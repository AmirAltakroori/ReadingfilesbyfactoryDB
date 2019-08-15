import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("File's name that you want to parse:  ");
        String receivedFile = in.next();

        ParsingFile parsingFile = new ParsingFile();

        FileParser fileParser = parsingFile.determineFile(receivedFile);

        System.out.println("Result's file name: ");
        String resultFile = in.next();

        fileParser.parseFile(resultFile);

    }
}

