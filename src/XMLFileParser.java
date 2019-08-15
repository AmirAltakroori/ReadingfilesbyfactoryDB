import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class XMLFileParser implements FileParser {

    private String fileName;

    protected XMLFileParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void parseFile(String resultFile) {
        try {
            File userFile = new File(fileName);
            FileOutputStream file = new FileOutputStream(resultFile);
            PrintWriter fileOut = new PrintWriter(file);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(userFile);
            doc.getDocumentElement().normalize();

            String typeName = doc.getDocumentElement().getNodeName().substring(0, 1).toUpperCase() + doc.getDocumentElement().getNodeName().substring(1);
            fileOut.println("Type: " + typeName);
            fileOut.println("------------------");

            NodeList nodes = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element note = (Element) node;
                    NodeList contentList = note.getChildNodes();
                    for (int j = 0; j < contentList.getLength(); j++) {
                        Node n = contentList.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element e = (Element) n;
                            fileOut.println(e.getTagName().substring(0, 1).toUpperCase() + e.getTagName().substring(1) + ": " + e.getTextContent());
                        }
                    }
                }
            }
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
