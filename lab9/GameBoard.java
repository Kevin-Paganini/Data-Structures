package paganiniK;


import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class GameBoard {
    private Cell[][] grid;
    public int gridSize;
    private final Collection<String> dictionary;
    private final int MAX_WORD_LENGTH = 16;
    Collection <String> foundWords = new HashSet<>();



    private static class Cell {

        int row;
        int col;
        char letter;


        public Cell(int col, int row, char letter) {
            this.col = col;
            this.row = row;
            this.letter = letter;

        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, letter);
        }

        @Override
        public boolean equals(Object o) {

            return this.hashCode() == o.hashCode();

        }
        public char getLetter() {
            return letter;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }
    }

    /**
     * Loads game board
     * @param dictionary
     */
    public GameBoard(Collection<String> dictionary) {
        this.dictionary = dictionary;
    }


    /**
     * recursive search method
     * @param isFourWay
     * @return found words
     */
    public Collection<String> findWords(boolean isFourWay){

        String text;

        HashSet<Cell> visited = new HashSet<>();
        for (int row=0; row < grid.length; row++) {
            for (int col=0; col < grid[row].length; col++) {


                text = "";
                foundWords.addAll(recursiveSearch(row, col, text, visited, isFourWay));
            }
        }
        return foundWords;
    }

    /**
     * private helper method to find words recursively
     * @param row
     * @param col
     * @param partialWord
     * @param visited
     * @param isFourWay
     * @return found words
     */
    private Collection<String> recursiveSearch(int row, int col, String partialWord, HashSet<Cell> visited, boolean isFourWay){

        Collection<String> words = new ArrayList<>();
        visited.add(grid[row][col]);
        if (partialWord.length() <= MAX_WORD_LENGTH) {
            partialWord += grid[row][col].letter;

            if (dictionary.contains(partialWord.toLowerCase()) && partialWord.length() >= 3) {

                if (!foundWords.contains(partialWord)) {
                    words.add(partialWord);
                }
            }
            // 4-WAY

            // increment/column row
            //make sure smaller than grid row and hasn't been visited yet
            if (row+1 < grid.length && !visited.contains(grid[row+1][col])) {
                words.addAll(recursiveSearch(row+1, col, partialWord, deepCopy(visited), isFourWay));
            }
            //make sure bigger than 0 and hasn't been visited
            if (!(row-1 < 0) && !visited.contains(grid[row-1][col])) {
                words.addAll(recursiveSearch(row-1, col, partialWord, deepCopy(visited), isFourWay));
            }
            // increment/decrement column
            //make sure smaller than column length and hasnt been visited
            if (col+1 < grid[0].length && !visited.contains(grid[row][col+1])) {
                words.addAll(recursiveSearch(row, col+1, partialWord, deepCopy(visited), isFourWay));
            }
            //make sure bigger than 0 and hasn't been visited yet
            if (!(col-1 < 0) && !visited.contains(grid[row][col-1])) {
                words.addAll(recursiveSearch(row, col-1, partialWord, deepCopy(visited), isFourWay));
            }

            // 8-WAY
            //Same idea here but with row and column checking NE, SE, SW, NW

            if(!isFourWay) {
                // increment row and column
                if ((row+1 < grid.length && col+1 < grid[0].length) && !visited.contains(grid[row+1][col+1])) {
                    words.addAll(recursiveSearch(row+1, col+1, partialWord, deepCopy(visited), isFourWay));
                }
                // decrement row and column
                if (!(row-1 < 0 || col-1 < 0) && !visited.contains(grid[row-1][col-1])) {
                    words.addAll(recursiveSearch(row-1, col-1, partialWord, deepCopy(visited), isFourWay));
                }
                // increment/decrement both
                if ((col+1 < grid[0].length && !(row-1 <0)) && !visited.contains(grid[row-1][col+1])) {
                    words.addAll(recursiveSearch(row-1, col+1, partialWord, deepCopy(visited), isFourWay));
                }
                if ((!(col-1 < 0) && row+1 < grid.length) && !visited.contains(grid[row+1][col-1])) {
                    words.addAll(recursiveSearch(row+1, col-1, partialWord, deepCopy(visited), isFourWay));
                }
            }
        }
        visited.remove(grid[row][col]);
        return words;
    }

    /**
     * Copies hashset over to make sure its accurate
     * @param copy
     * @return hashset of visited cells
     */
    private HashSet<Cell> deepCopy(HashSet<Cell> copy){
        HashSet<Cell> set1;
        set1 = (HashSet<Cell>) copy.clone();
        return set1;
    }

    /**
     * loads dictionary to compare words found in the grid
     * @param path
     * @return various collections of words
     */
    public Collection<String> loadDictionary(Path path) {

        try(Scanner in = new Scanner(path.toFile())) {

            while(in.hasNext()) {
                dictionary.add(in.next());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    /**
     * Loads grid for word search
     * @param path
     * @return grid of letters
     */
    public Cell[][] loadGrid(Path path) {

        try(Scanner in = new Scanner(path.toFile())) {
            //Grid number row
            int row = Integer.parseInt(path.toFile().toString().substring(9, 10));
            //grid number col
            int col = Integer.parseInt(path.toFile().toString().substring(11, 12));
            grid = new Cell[row][col];

            int r = 0;
            while(in.hasNext()) {
                String read = in.next();

                for(int c = 0; c < read.length(); ++c){
                    grid[r][c] = new Cell(r, c, read.charAt(c));
                }
                r++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return grid;
    }






}

