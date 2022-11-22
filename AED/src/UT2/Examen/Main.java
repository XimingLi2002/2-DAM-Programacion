package UT2.Examen;

import UT2.Examen.Interfaces.DDBB_Resources;
import UT2.Examen.Utilities.BufferedIO;
import UT2.Examen.Utilities.DDBB;


import java.sql.SQLException;

public class Main implements DDBB_Resources {

    public static void main(String[] args) throws SQLException {
        String SQLSentencesPath = "src/UT2/Examen/Utilities/SQLSentences.SQL";
        //Conexion a BBDD
        DDBB OracleSQL = new DDBB(ORACLE_DRIVER, ORACLE_CONNECTION, ORACLE_USERNAME, ORACLE_PASSWORD);
        for (String sentence : new BufferedIO(SQLSentencesPath).readFile()) {
            if (sentence.toLowerCase().contains("delete") ||sentence.toLowerCase().contains("insert")) {
                OracleSQL.executeUpdate(sentence);
            }else{
                OracleSQL.executeSelect(sentence);
            }
        }


        System.out.println();
        System.out.println("||=================================||ANTES DEL PROCEDURE||=================================||");
        OracleSQL.executeSelect("SELECT * FROM oficinas WHERE localidad = 'Arucas' OR localidad = 'Galdar'");
        OracleSQL.procedure("SELECT * FROM oficinas WHERE localidad = 'Arucas'");
        System.out.println("||================================||DESPUES DEL PROCEDURE||================================||");
        OracleSQL.executeSelect("SELECT * FROM oficinas WHERE localidad = 'Arucas' OR localidad = 'Galdar'");
        OracleSQL.closeConnection();



    }
}
