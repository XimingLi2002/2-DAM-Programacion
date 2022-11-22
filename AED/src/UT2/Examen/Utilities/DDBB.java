package UT2.Examen.Utilities;

import UT2.Examen.Main;
import UT2.ManejoDeConectores.Interfaces.ConsoleColors;
import UT2.ManejoDeConectores.Interfaces.SQLDataTypes;

import java.sql.*;



public class DDBB implements ConsoleColors, SQLDataTypes {

    //TODO quizas ocurra algun error por el final
    private final Connection connection;
    private final Statement statement;
    private int[] COLUMN_DATA_TYPE;

    public DDBB(String DDBB_Driver, String DDBB_Connection, String DDBB_Username, String DDBB_Password){
        try {
            Class.forName(DDBB_Driver);
            connection = DriverManager.getConnection(DDBB_Connection, DDBB_Username, DDBB_Password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DDBB(String DDBB_Driver, String DDBB_Connection){
        try {
            Class.forName(DDBB_Driver);
            connection = DriverManager.getConnection(DDBB_Connection);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void executeUpdate(String sentence) throws SQLException {
        statement.executeUpdate(sentence);
    }
    public void executeSelect(String sentence) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sentence);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        COLUMN_DATA_TYPE = new int[resultSetMetaData.getColumnCount()];
        printSelect(resultSet, resultSetMetaData);
        resultSet.close();
    }

    private void printSelect(ResultSet resultSet, ResultSetMetaData resultSetMetaData) throws SQLException {
        System.out.println(RED_BOLD);
        printCols(resultSetMetaData);
        System.out.println(RESET);
        printDataContent(resultSet);
        System.out.println(BLUE_BOLD + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + RESET);
    }

    private void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) {
            System.out.printf("%-30s", resultSetMetaData.getColumnName(i).toUpperCase());
            COLUMN_DATA_TYPE[i - 1] = resultSetMetaData.getColumnType(i);
        }
    }

    private void printDataContent(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) { // 03  012  13 123
                System.out.printf("%-30s", readData(resultSet, COLUMN_DATA_TYPE[i - 1], i));
            }
            System.out.println();
        }
    }

    private String readData(ResultSet resultSet, int dataType, int columPos) throws SQLException {
        String dataValue = "";
        switch (dataType) {
            case SMALLINT, INTEGER, NUMERIC, BIGINT -> dataValue = String.valueOf(resultSet.getInt(columPos));
            case VARCHAR, DATE -> dataValue = resultSet.getString(columPos);
            case DOUBLE -> dataValue = String.valueOf(resultSet.getDouble(columPos));
            case CHAR -> dataValue = String.valueOf(resultSet.getString(columPos));
            case TIMESTAMP ->  dataValue = String.valueOf(resultSet.getDate(columPos));
            default -> System.err.print("Falta case " + dataType);
        }
        return dataValue;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error DDBB.closeConnection");
            throw new RuntimeException(e);
        }
    }

    //ORACLE PROCEDURE
    public void executeProcedure(String procedureName,String[] paramData){
        int paramLength = paramData.length;
        try {
            CallableStatement callableStatement = connection.prepareCall(getProcedureQuery(procedureName, paramLength));
            //{call procedureName ('?','?')} -> sustituye las '?' por parametros
            for (int i = 1; i <= paramLength; i++) callableStatement.setString(i, paramData[i - 1]);
            callableStatement.execute();
        }catch (SQLException ignored){}
    }
    //Obtiene la sentencia del procedimiento en base a su nombre y el número de parámetros.
    private String getProcedureQuery(String procedureName, int paramAmount) {
        StringBuilder query = new StringBuilder("{call "+ procedureName +"()}");
        //Añade un '?' por cada parametro para posteriormente sustituirlo por un parametro
        int pos = query.indexOf("(")+1;
        for (int i = 0; i< paramAmount; i++){
            if (!(i== paramAmount -1)) {
                query.insert(pos,"?,");
                pos+=2;
            }else {
                query.insert(pos,"?");
            }
        }
        return query.toString();
    }

    public void procedure(String sentence) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sentence);
        while(resultSet.next()){
            executeProcedure("MODIFICA_LOCALIDAD", new String[]{String.valueOf(resultSet.getInt(1))});
        }
    }
}
