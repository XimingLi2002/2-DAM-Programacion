package UT2.ManejoDeConectores.Interfaces;

public interface DDBB_Resources {
    String POSTGRE_DRIVER = "org.postgresql.Driver";
    String POSTGRE_IP = "localhost:5432";
    String POSTGRE_DDBB_NAME = "Ximing_AED";
    String POSTGRE_CONNECTION = "jdbc:postgresql://"+ POSTGRE_IP +"/"+ POSTGRE_DDBB_NAME;
    String POSTGRE_USERNAME = "openpg";
    String POSTGRE_PASSWORD = "openpgpwd";
    //String POSTGRE[] = new String[]{POSTGRE_DRIVER, POSTGRE_CONNECTION, POSTGRE_USERNAME, POSTGRE_PASSWORD};



    String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    String ORACLE_CONNECTION = "jdbc:oracle:thin:@localhost/XE";
    String ORACLE_USERNAME = "ximing_aed";
    String ORACLE_PASSWORD = "Xx1216";
}
