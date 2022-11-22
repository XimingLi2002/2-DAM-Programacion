package UT2.PostgreSQL;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Registramos el driver de PostgresSQL
            Class.forName("org.postgresql.Driver");

            // Conectamos con la base de datos
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "openpg", "openpgpwd");

            Statement statement = connection.createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select * from departamentos;");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnType(1));
            System.out.println(resultSetMetaData.getColumnTypeName(1));

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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

