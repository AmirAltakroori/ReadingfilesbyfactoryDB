import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JSONFileParser implements FileParser{

    private String fileName;

    protected JSONFileParser (String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public void parseFile(String resultFile) {

        try {
            File userFile = new File (fileName);
            PrintWriter fileOut = new PrintWriter(new FileOutputStream(new File(resultFile)));

            FileReader reader = new FileReader(userFile);
            Object object = new JSONParser().parse(reader);
            JSONObject jsonObject = (JSONObject) object;

            fileOut.println(("Type: Note\n------------------"));

            JSONObject noteDetails = (JSONObject) jsonObject.get("note");
            fileOut.println("To: " + noteDetails.get("to"));
            fileOut.println("From: " + noteDetails.get("from"));
            fileOut.println("Heading: " + noteDetails.get("heading"));
            fileOut.println("Body: " + noteDetails.get("body"));


            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
