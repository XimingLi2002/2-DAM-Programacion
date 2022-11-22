package UT2.SQLite;

import java.sql.*;

public class Main {

    private static final String DDBB_Connection = "jdbc:sqlite:D:\\Programacion\\AED\\src\\UT2\\SQLite\\Empleados&Departamentos.db";
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DDBB_Connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select * from departamentos;");
            System.out.println("Tabla departamento:");
            while(resultSet.next()){
                System.out.println("Depno: " +resultSet.getInt(1) +
                        "     Nombre: " + resultSet.getString(2) +
                        "     Localidad: " + resultSet.getString(3));
            }

            resultSet = statement.executeQuery("select * from empleados;");
            System.out.println("Tabla empleados:");
            while(resultSet.next()){
                System.out.println("Empno: " +resultSet.getInt(1) +
                        "     Nombre: " + resultSet.getString(2) +
                        "     Cargo: " + resultSet.getString(3) +
                        "     FechaIng: " + resultSet.getString(4) +
                        "     Salario: " + resultSet.getDouble(5) +
                        "     Comisión: " + resultSet.getDouble(6) +
                        "     Deptno: " + resultSet.getInt(7));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
