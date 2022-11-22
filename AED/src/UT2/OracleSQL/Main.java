package UT2.OracleSQL;

import UT2.RepasoSQL.Interfaz.SQLDataTypes;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class Main implements SQLDataTypes {
    private static final String DDBB_Connection = "jdbc:oracle:thin:@localhost/XE";
    private static final String DDBB_Username = "ximing_aed";
    private static final String DDBB_Password = "Xx1216";
    private static final String SQLITE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static Connection connection;
    private static Statement statement;
    private static int[] COLUMN_DATA_TYPE;

    public static void main(String[] args) {
        try {
            Class.forName(SQLITE_DRIVER);
            OracleDataSource oracleDataSource = new OracleDataSource();
            oracleDataSource.setURL(DDBB_Connection);
            oracleDataSource.setUser(DDBB_Username);
            oracleDataSource.setPassword(DDBB_Password);
            //connection = DriverManager.getConnection(DDBB_Connection,DDBB_Username,DDBB_Password);
            connection = oracleDataSource.getConnection();
            statement = connection.createStatement();
            executeSelect("select * from DEPARTAMENTOS");

            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Ejecuta una sentencia SELECT y printea por consola su contenido
    private static void executeSelect(String sentence) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sentence);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        COLUMN_DATA_TYPE = new int[resultSetMetaData.getColumnCount()];
        printSelect(resultSet, resultSetMetaData);
        resultSet.close();
    }

    //Llama a los métodos para imprimir el nombre de las columnas y los registros de un SELECT
    private static void printSelect(ResultSet resultSet,ResultSetMetaData resultSetMetaData) throws SQLException {
        printCols(resultSetMetaData);
        System.out.println();
        printDataContent(resultSet);
    }

    //Printea el nombre de las columnas de la tabla.
    private static void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) {
            System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            COLUMN_DATA_TYPE[i - 1] = resultSetMetaData.getColumnType(i);
        }
    }

    //Printea todos los datos de una tabla.
    private static void printDataContent(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) { // 03  012  13 123
                System.out.printf("%-20s", readData(resultSet, COLUMN_DATA_TYPE[i - 1], i));
            }
            System.out.println();
        }
    }

    private static String readData(ResultSet resultSet, int dataType, int columPos) throws SQLException {
        String dataValue = "";
        switch (dataType) {
            case SMALLINT, NUMERIC -> dataValue = String.valueOf(resultSet.getInt(columPos));
            case VARCHAR, DATE -> dataValue = resultSet.getString(columPos);
            case DOUBLE -> dataValue = String.valueOf(resultSet.getDouble(columPos));
            case CHAR -> dataValue = String.valueOf(resultSet.getString(columPos));
            default -> System.out.print("Falta case para " + dataType);
        }
        return dataValue;
    }
}
