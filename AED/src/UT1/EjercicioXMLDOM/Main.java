package UT1.EjercicioXMLDOM;


public class Main {
    public static String filePathOBJ = "./src/UT1.EjercicioXMLDOM/centroEducativo.obj";
    public static String filePathXML = "./src/UT1.EjercicioXMLDOM/" + xmlConstants.documentName + ".xml";

    public static void main(String[] args) {
        objectIO.createObjects();
        objectIO.readObjects();
        xmlIO.setWriteDocumentStructure();
        xmlIO.createNode();
        xmlIO.createXMLFile();
        xmlIO.readXMLFile();
    }
}
