package paganiniK;


import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class WordSearchCLI {

    /**
     * Driver for the program
     * @param args
     */
    public static void main(String[] args) {

        if(args.length < 4){
            throw new IllegalArgumentException("You need to pass in four arguments");
        }
        if(!args[0].equalsIgnoreCase("4way") && !args[0].equalsIgnoreCase("8way")){
            throw new IllegalArgumentException("Invalid method...");
        }
        if(!args[3].equalsIgnoreCase("ArrayList") &&
                !args[3].equalsIgnoreCase("LinkedList") &&
                !args[3].equalsIgnoreCase("HashSet") &&
                !args[3].equalsIgnoreCase("TreeSet")){
            throw new IllegalArgumentException("Invalid dataset.");
        }
        //CMD line arguments
        String is4Way = args[0];
        String grid = args[1];
        String wordList = args[2];
        String dataStructure = args[3];
        GameBoard gameBoard;

        if(is4Way.equals("4way")){

            //Initializing game board
            if(dataStructure.equalsIgnoreCase("ArrayList")) {
                gameBoard = new GameBoard(new ArrayList<>());
            } else if (dataStructure.equalsIgnoreCase("LinkedList")) {
                gameBoard = new GameBoard(new LinkedList<>());
            } else if(dataStructure.equalsIgnoreCase("HashSet")) {
                gameBoard = new GameBoard(new HashSet<>());
            } else {
                gameBoard = new GameBoard(new TreeSet<>());
            }

            //Loading files
            Path wordTxt = new File(wordList).toPath();
            gameBoard.loadDictionary(wordTxt);
            Path gridTxt = new File(grid).toPath();
            gameBoard.loadGrid(gridTxt);


            //Timer
            long start = System.nanoTime();
            Collection<String> foundWords = gameBoard.findWords(true);
            long end = System.nanoTime();

            //print it
            Iterator iterator = foundWords.iterator();
            while(iterator.hasNext()){
                String word = (String) iterator.next();
                System.out.println(word);
            }
            System.out.println("Number of found words " + foundWords.size());
            long time = end - start;
            double timeInSec = time / 1000000000;
            System.out.println("Time taken to do the search: " + String.format("%.2f",timeInSec) + " seconds");



        } else {
            //Initializing game board
            if(dataStructure.equalsIgnoreCase("ArrayList")) {
                gameBoard = new GameBoard(new ArrayList<>());
            } else if (dataStructure.equalsIgnoreCase("LinkedList")) {
                gameBoard = new GameBoard(new LinkedList<>());
            } else if(dataStructure.equalsIgnoreCase("HashSet")) {
                gameBoard = new GameBoard(new HashSet<>());
            } else {
                gameBoard = new GameBoard(new TreeSet<>());
            }

            //Loading files
            Path wordTxt = new File(wordList).toPath();
            gameBoard.loadDictionary(wordTxt);
            Path gridTxt = new File(grid).toPath();
            gameBoard.loadGrid(gridTxt);

            //Timer
            long start = System.nanoTime();
            Collection<String> foundWords = gameBoard.findWords(false);
            long end = System.nanoTime();

            //print it
            Iterator iterator = foundWords.iterator();
            while(iterator.hasNext()){
                String word = (String) iterator.next();
                System.out.println(word);
            }
            System.out.println("Number of found words " + foundWords.size());
            long time = end - start;
            double timeInSec = time / 1000000000;
            System.out.println("Time taken to do the search: " + String.format("%.2f",timeInSec) + " seconds");

        }

    }





}
