package UT1.EjercicioXMLDOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static UT1.EjercicioXMLDOM.Main.filePathXML;

public class xmlIO {
    private static DocumentBuilderFactory documentBuilderFactory;
    private static DocumentBuilder documentBuilder;
    private static DOMImplementation domImplementation;
    private static Document document;

    public static ArrayList<CentroEducativo> centroEducativosList = new ArrayList<>();
    public static void main(String[] args) {
        //objectIO.readObjects();
        setWriteDocumentStructure();
        createNode();
        createXMLFile();
        readXMLFile();
    }

    protected static void readXMLFile(){
        /*
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        */
        try {
            document = documentBuilder.parse(new File(filePathXML));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();

        System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());

        //nodeList == documentName que sería el elemento raíz (padre)
        NodeList nodeList = document.getElementsByTagName(xmlConstants.elementName);
        for (int i = 0; i < xmlConstants.fields.length; i++){
            System.out.printf("%-30s", xmlConstants.fields[i].toUpperCase());
        }
        System.out.println();
        for (int i = 0; i < nodeList.getLength(); i++) {
            //node == elementName que sería el elemento hijo
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementChild = (Element) node;
                for (int j = 0; j < xmlConstants.fields.length; j++){
                    //System.out.print(xmlConstants.fields[j] + ": " + readNode(xmlConstants.fields[j], elementChild)+"     ");
                    System.out.printf("%-30s", readNode(xmlConstants.fields[j], elementChild));
                }
                System.out.println();
            }
        }
    }
    private static String readNode(String fieldName, Element elementChild) {
        NodeList node = elementChild.getElementsByTagName(fieldName).item(0).getChildNodes();
        Node nodeValor = (Node) node.item(0);
        return nodeValor.getNodeValue();
    }

    protected static void setWriteDocumentStructure() {
        // Se crea una instancia de DocumentBuilderFactory para construir el parser
        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // Para crear el doc. vacío con versión de XML
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        domImplementation = documentBuilder.getDOMImplementation();
        //document == elementRoot (elemento raíz)
        document = domImplementation.createDocument(null, xmlConstants.documentName, null);
        document.setXmlVersion("1.0");
    }

    protected static void createXMLFile() {
        //Crea la fuente XML a partir del documento
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(filePathXML));
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            //Y realiza la transformación de documento a fichero
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    protected static void createNode() {
        for (int i = 0; i < centroEducativosList.size(); i++) {
            //ElementoHijo / nodo
            Element elementChild = document.createElement(xmlConstants.elementName);
            //añado al el elemento hijo o nodo al elemento raiz (padre)
            document.getDocumentElement().appendChild(elementChild);
            String[] centroEducativoAttributes = new String[]{centroEducativosList.get(i).getTipo(), centroEducativosList.get(i).getNombre(),
                    String.valueOf(centroEducativosList.get(i).getCodigo()), centroEducativosList.get(i).getCalle(),
                    String.valueOf(centroEducativosList.get(i).getCodigoPostal()), centroEducativosList.get(i).getLocalidad(),
                    centroEducativosList.get(i).getIsla()};
            for (int j = 0; j < xmlConstants.fields.length; j++) {
                createField(centroEducativoAttributes[j], xmlConstants.fields[j], elementChild);
            }
        }
    }

    private static void createField(String fieldValor, String fieldName, Element elementChild) {
        //Crea el campo
        Element field = document.createElement(fieldName);
        //Crea el texto a introducir al campo
        Text text = document.createTextNode(fieldValor);
        //Añade el texto al campo
        field.appendChild(text);
        //Añade el campo al elemento hijo (nodo)
        elementChild.appendChild(field);
    }
}
