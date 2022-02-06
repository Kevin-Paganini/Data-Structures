package paganiniK;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseEncoder{
    public static HashMap hashMap = new HashMap<>();


    public static HashMap getHashMap() {
        return hashMap;
    }

    public static void loadTable(Path path){
        File morseFile = path.toFile();
        String symbol;
        String code;
        try(Scanner in = new Scanner(morseFile)) {

            while(in.hasNextLine()) {
                String s = in.nextLine();
                if(s.startsWith("\\n")){
                    symbol = s.substring(0, 2);
                    code = s.substring(2);
                } else {
                    symbol = s.substring(0, 1);

                    code = s.substring(1);
                }
                hashMap.put(symbol, code);


            }





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String encodeMessage(String message){
        ArrayList<String> skip = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            try {
                String key = message.substring(i, i +1).toUpperCase();
                if(hashMap.get(key) != null) {
                    if (key.equals("\\n")) {
                        //sb.append("\\n");
                    }
                    sb.append(hashMap.get(key)).append(" ");

                } else {
                    skip.add(key);
                }
            } catch (IllegalArgumentException e) {
                skip.add(e.getMessage());
            }
        }

        StringBuilder skipSB = new StringBuilder();
        for(int i = 0; i < skip.size(); ++i) {
            skipSB.append(skip.get(i)).append(", ");
        }
        if (skip.size() > 0) {
            System.err.println("Warning: Skipping: " + skipSB.substring(0, skipSB.length() - 2));
        }


        return sb.toString();

    }






}