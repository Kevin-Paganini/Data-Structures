package paganiniK;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Decodes a morse file that the user specifies
 */
public class MorseDecoder {
    /**
     * Binary tree made through morseCode.txt file and used to decode any morse file
     */
    public static MorseTree morseTree = new MorseTree();



    public static void main(String[] args) throws IOException {





        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println("please enter a file to be decoded.");
            File inputFile = new File("data\\" + userInput.next().trim());
            System.out.println("please enter an output file.");
            File outputFile = new File("data\\" + userInput.next().trim());
            File file = new File("data\\morseCode.txt");
            loadDecoder(file);
            decodeFile(inputFile, outputFile);



        } catch (InputMismatchException | IOException e) {
            System.out.println("Trouble with input output of the file...");
        }
    }

    private static void loadDecoder(File file) throws IOException {
        String symbol;
        String code;
        try(Scanner in = new Scanner(file)) {

            while(in.hasNextLine()) {
                String s = in.nextLine();
                if(s.startsWith("\\n")){
                    symbol = s.substring(0, 2);
                    code = s.substring(2);
                } else {
                    symbol = s.substring(0, 1);

                    code = s.substring(1);
                }

                morseTree.add(symbol, code);
            }
        }
    }

    private static void decodeFile(File inputFile, File outputFile) {

        try (Scanner reader = new Scanner(inputFile)) {
            try(PrintWriter pw = new PrintWriter(outputFile)) {
                while(reader.hasNextLine()){
                    Scanner lineParser = new Scanner(reader.nextLine());
                    while(lineParser.hasNext()){
                        String next = lineParser.next().trim();

                        if(next.startsWith("-") || next.startsWith(".")) {
                            String symbol = (String) morseTree.decode(next);
                            if(symbol == null){
                                System.err.println("Invalid morse code found in file.");
                            } else if(symbol.equalsIgnoreCase("\\n")) {
                                pw.println();
                            } else {
                                pw.print(symbol);
                            }
                        } else {
                            System.err.println("Warning: skipping: " + next);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found...");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
    }
}