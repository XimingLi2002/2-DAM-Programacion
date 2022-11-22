package UT2.ManejoDeConectores;

import UT2.ManejoDeConectores.Utilities.BufferedIO;
import UT2.ManejoDeConectores.Utilities.DDBB;
import UT2.ManejoDeConectores.Interfaces.DDBB_Resources;

import java.sql.SQLException;

public class Main implements DDBB_Resources {
    public static void main(String[] args) throws SQLException {
        String insertsPATH = "src/UT2/ManejoDeConectores/SQLSentences/InsertValues.sql";
        String selectsPATH = "src/UT2/ManejoDeConectores/SQLSentences/SelectValues.sql";

        System.err.println("\nPOSTGRESQL\n");
        DDBB PostgreSQL = new DDBB(POSTGRE_DRIVER, POSTGRE_CONNECTION, POSTGRE_USERNAME, POSTGRE_PASSWORD);
        for (String sentence : new BufferedIO(insertsPATH).readFile()) PostgreSQL.executeUpdate(sentence);
        for (String sentence : new BufferedIO(selectsPATH).readFile()) PostgreSQL.executeSelect(sentence);
        PostgreSQL.closeConnection();

        System.err.println("\nORACLESQL\n");
        DDBB OracleSQL = new DDBB(ORACLE_DRIVER, ORACLE_CONNECTION, ORACLE_USERNAME, ORACLE_PASSWORD);
        for (String sentence : new BufferedIO(insertsPATH).readFile()) OracleSQL.executeUpdate(sentence);
        for (String sentence : new BufferedIO(selectsPATH).readFile()) OracleSQL.executeSelect(sentence);

        System.out.println("\nTRIGGER");
        OracleSQL.executeUpdate("DELETE FROM pedidos_pendientes");
        OracleSQL.executeSelect("SELECT * FROM pedidos_pendientes");
        OracleSQL.executeUpdate("UPDATE productos SET stock = stock-8 WHERE cod_prod = 6");
        OracleSQL.executeSelect("SELECT * FROM pedidos_pendientes");

        System.out.println();
        System.out.println("||=================================||ANTES DEL PROCEDURE||=================================||");
        OracleSQL.executeSelect("SELECT * FROM proveedores WHERE cod_prov = 1");
        //nombre del procedure, (cod_prov y porcentaje adicional)
        OracleSQL.executeProcedure("MODIFICA_BONIFICACION", new String[]{"1", "10"});
        System.out.println("||================================||DESPUES DEL PROCEDURE||================================||");
        OracleSQL.executeSelect("SELECT * FROM proveedores WHERE cod_prov = 1");
        OracleSQL.closeConnection();


    }
}
