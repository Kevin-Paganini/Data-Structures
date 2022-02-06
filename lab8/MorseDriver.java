
package paganiniK;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Driver for the program.
 */
public class MorseDriver {

    public static HashMap hashMap;

    /**
     * Runs the whole program.
     * @param args
     */
    public static void main(String[] args) {

        try {

            Path inputPath = new File(args[0]).toPath();

            Path outputPath = new File(args[1]).toPath();
            Path morseCode = new File("..\\..\\..\\data\\morsecode.txt").toPath();
            MorseEncoder.loadTable(morseCode);
            hashMap = MorseEncoder.getHashMap();
            readAndWriteFile(inputPath, outputPath);







        }  catch (InputMismatchException e) {
            System.out.println("Trouble with input output of the file...");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found...");
        }

    }

    private static void readAndWriteFile(Path inPath, Path outPath) throws FileNotFoundException {
        try (Scanner in = new Scanner(inPath.toFile())) {
            try (PrintWriter pw = new PrintWriter(outPath.toFile())) {
                while (in.hasNextLine()) {
                    Scanner lineParser = new Scanner(in.nextLine());
                    while (lineParser.hasNext()) {

                        String eval = lineParser.next();

                        String code = MorseEncoder.encodeMessage(eval);

                        pw.print(code + " ");

                    }

                    pw.println();

                }


            }
        }
    }


}