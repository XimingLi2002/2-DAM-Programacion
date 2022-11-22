package UT2.RepasoSQL;

import UT2.RepasoSQL.Interfaz.ConsoleColors;
import UT2.RepasoSQL.Interfaz.SQLDataTypes;

import java.sql.*;

public class Main implements SQLDataTypes, ConsoleColors {

    private static Statement statement;
    private static int[] COLUMN_DATA_TYPE;

    public static void main(String[] args) {
        final String DDBB_Connection1 = "jdbc:sqlite:./src/UT2/RepasoSQL/Empleados&Departamentos.db";
        final String DDBB_Connection2 = "jdbc:sqlite:E:./src/UT2/RepasoSQL/Proveedores&Productos.db";
        final String SQLITE_DRIVER = "org.sqlite.JDBC";

        try {
            Class.forName(SQLITE_DRIVER);
            Connection connection = DriverManager.getConnection(DDBB_Connection1);
            statement = connection.createStatement();

            //Para insertar datos;
            //statement = connection.prepareStatement("INSERT INTO ? VALUES (?,?,?,?,?)");

            executeSelect(SQLSentences.SENTENCE_1);
            executeSelect(SQLSentences.SENTENCE_2);
            executeSelect(SQLSentences.SENTENCE_3);
            executeSelect(SQLSentences.SENTENCE_4);
            connection = DriverManager.getConnection(DDBB_Connection2);
            statement = connection.createStatement();
            executeSelect(SQLSentences.SENTENCE_5);
            executeSelect(SQLSentences.SENTENCE_6);
            executeSelect(SQLSentences.SENTENCE_7);
            executeSelect(SQLSentences.SENTENCE_8);
            executeSelect(SQLSentences.SENTENCE_9);

            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void executeSelect(String sentence) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sentence);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        COLUMN_DATA_TYPE = new int[resultSetMetaData.getColumnCount()];
        printSelect(resultSet, resultSetMetaData);
        resultSet.close();
    }

    private static void printSelect(ResultSet resultSet,ResultSetMetaData resultSetMetaData) throws SQLException {
        System.out.println(RED_BOLD);
        printCols(resultSetMetaData);
        System.out.println(RESET);
        printDataContent(resultSet);
        System.out.println(BLUE_BOLD + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + RESET);
    }

    private static void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) {
            System.out.printf("%-30s", resultSetMetaData.getColumnName(i));
            COLUMN_DATA_TYPE[i - 1] = resultSetMetaData.getColumnType(i);
        }
    }

    private static void printDataContent(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) { // 03  012  13 123
                System.out.printf("%-30s", readData(resultSet, COLUMN_DATA_TYPE[i - 1], i));
            }
            System.out.println();
        }
    }

    private static String readData(ResultSet resultSet, int dataType, int columPos) throws SQLException {
        String dataValue = "";
        switch (dataType) {
            case SMALLINT -> dataValue = String.valueOf(resultSet.getInt(columPos));
            case VARCHAR, DATE -> dataValue = resultSet.getString(columPos);
            case DOUBLE -> dataValue = String.valueOf(resultSet.getDouble(columPos));
            case CHAR -> dataValue = String.valueOf(resultSet.getString(columPos));
            default -> System.out.print("Falta case para " + dataType);
        }
        return dataValue;
    }
}
