package paganiniK;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Helps read in files
 */
public class FileReaderUtils {

    /**
     * Checks if line contains return with nothing else
     * @param line
     * @return return true or false
     */
    public boolean isVoidReturn(String line) {
        boolean isVoidReturn;
        String lineTrim = line.trim();
        if(lineTrim.contentEquals("return")){
            isVoidReturn = true;
        } else {
            isVoidReturn = false;
        }

        return isVoidReturn;
    }

    /**
     * Checks if line contains return with an int
     * @param line
     * @return parses return and int
     */
    public OptionalInt parseReturnValue(String line){
        OptionalInt OI;
        String lineTrim = line.trim();
        if(lineTrim.contains("return")){
            String s = lineTrim.split("return ")[1];
            OI = OptionalInt.of(Integer.parseInt(s));
        } else {
            OI = OptionalInt.empty();
        }
        return OI;
    }

    /**
     * parses the method name
     * @param line
     * @return an optional string
     */
    public Optional<String> parseMethodName(String line){
        String lineTrim = line.trim();
        String methodName = "";
        Optional<String> methodName1;
        if(lineTrim.contains("(")){


            if(lineTrim.contains("void")){
                lineTrim = getRidOfNumbers(lineTrim);
                lineTrim = lineTrim.replace("void", "");
                methodName = lineTrim;

            } else if (lineTrim.contains("int")){
                lineTrim = getRidOfNumbers(lineTrim);
                lineTrim = lineTrim.replace("int", "");
                methodName = lineTrim;

            } else {

                methodName = lineTrim.replace("()", "");




            }
            methodName1 = Optional.of(methodName);
        } else {
            methodName1 = Optional.empty();
        }


        return methodName1;

    }

    /**
     * How many arguments between parentheses
     * @param line
     * @return arguments from the method call
     */
    public int[] parseArguments(String line){

        int[] parseInt = null;

        int howManyArguments = 0;



        if(!line.contains("(")){
            parseInt = null;
        } else {

            String lineTrim = getRidOfLetters(line);

            String[] lineParse = lineTrim.split(",");
            int check = lineParse.length;
            if(lineParse.equals("")){
                parseInt = new int[0];
            } else {
                parseInt = new int[lineParse.length];

                for (String s : lineParse) {
                    try {


                        parseInt[howManyArguments] = Integer.parseInt(s);
                        howManyArguments++;
                    } catch (NumberFormatException e) {

                        parseInt = new int[0];


                    }

                }
            }

        }
        return parseInt;
    }


    /**
     * Helper method to get rid of numbers in a string
     * @param name
     * @return string with no numbers
     */

    private String getRidOfNumbers(String name) {
        return name.replaceAll("[^a-zA-Z]", "");
    }

    /**
     * Helper method to get rid of letters in a string
     * @param name
     * @return string with no letters
     */
    private String getRidOfLetters(String name){
        return name.replaceAll("[^0-9,]", "");
    }
}


