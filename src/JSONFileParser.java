import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class JSONFileParser implements FileParser {

    private String fileName;

    protected JSONFileParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void parseFile(String resultFile) {

        try {
            FileOutputStream file = new FileOutputStream(resultFile);
            PrintWriter fileOut = new PrintWriter(file);


            String contents = new String(Files.readAllBytes(Paths.get(fileName)));

            org.json.JSONObject jsonObject = new org.json.JSONObject(contents);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                fileOut.println(key.substring(0, 1).toUpperCase() + key.substring(1));
                fileOut.println("------------------");

                JSONArray nodesList = jsonObject.getJSONArray(key);

                for (int i = 0; i < nodesList.length(); i++) {
                    org.json.JSONObject node = (JSONObject) nodesList.get(i);
                    Iterator<String> nodeKeys = node.keys();
                    while (nodeKeys.hasNext()) {
                        String nodeKey = nodeKeys.next();
                        fileOut.println(nodeKey.substring(0, 1).toUpperCase() + nodeKey.substring(1) + ": " + node.get(nodeKey));
                    }

                }
            }
            fileOut.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
