import java.io.File;

public class ParsingFile {
    public FileParser determineFile (String fileName)
    {
        String fileType = fileName.substring(fileName.lastIndexOf('.')+1);

        if (fileType == null)
            return null;
        else if (fileType.equalsIgnoreCase("json"))
            return new JSONFileParser(fileName);
        else if (fileType.equalsIgnoreCase("xml"))
            return new XMLFileParser(fileName);
        else
            return null;
    }
}
