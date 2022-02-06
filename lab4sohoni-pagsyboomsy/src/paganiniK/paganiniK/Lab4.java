package paganiniK;





import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Driver class to run the file
 */
public class Lab4 {
    public static void main(String[] args) {


//C:\Users\paganinik\IdeaProjects\Data Structures\lab4sohoni-pagsyboomsy\src\paganiniK\calls.txt

        ProgramStack stack = new ProgramStack();

        System.out.println("Please give me an input file.");
        Scanner UI = new Scanner(System.in);

        String inputFileUI = UI.next();
        File inputFile = new File("C:\\Users\\paganinik\\IdeaProjects\\Data Structures\\" +
                "lab4sohoni-pagsyboomsy\\src\\paganiniK\\" + inputFileUI);
        try(Scanner in = new Scanner(inputFile)){
            FileReaderUtils FRU = new FileReaderUtils();
            while(in.hasNextLine()){
                String input = in.nextLine();
                //Debug each line, change .contains method
                if(input.contains("int two")){
                    System.out.println("hi");
                }
                if(FRU.isVoidReturn(input)){
                    stack.returnFromMethod();

                } else if(FRU.parseReturnValue(input).isPresent()) {
                    int returnValue = FRU.parseReturnValue(input).getAsInt();
                    stack.returnFromMethod(returnValue);

                } else if(FRU.parseMethodName(input).isPresent()) {

                    String name = FRU.parseMethodName(input).get();
                    int[] arguments = FRU.parseArguments(input);
                    stack.callMethod(name, arguments);



                }
                System.out.println(stack.toString());

                Scanner pause = new Scanner(System.in);
                System.out.println("Move on press any key and enter");
                String pauseU = pause.next();

            }


        } catch (NoSuchElementException | FileNotFoundException e){
            System.out.println("I/O exception...");
        }


    }


}


