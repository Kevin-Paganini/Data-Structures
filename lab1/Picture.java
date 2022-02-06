package paganiniK;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Creates a picture out of dots and connects those dots with lines
 */
public class Picture {

    /**
     * keeps track of all the dots in a picture
     */
    private List<Dot> dots;
    private Collection<Dot> collection;

    private final double DOT_WIDTH = 6;


    /**
     * Creates a new List
     * @param nullList
     */
    public Picture(List<Dot> nullList){
        dots = nullList;
    }

    /**
     * copies a picture to a new picture
     * @param original
     * @param nullList
     */
    public Picture(Picture original, List<Dot> nullList){
        nullList.addAll(original.dots);
        dots = nullList;
    }


    /**
     * getter
     * @return number of dots
     */
    public int getNumDots(){
        return dots.size();
    }

    /**
     * Loads a .dot file
     * @param path
     * @throws IOException
     */
    public void load(Path path) throws IOException {
        try(Scanner in = new Scanner(Files.newInputStream(path))){
            dots.clear();
            while(in.hasNextLine()){
                String[] values = in.nextLine().split(",");
                double xCoord = Double.parseDouble(values[0].trim());
                double yCoord = Double.parseDouble(values[1].trim());
                if ((xCoord >=0 && xCoord <=1) && (yCoord >=0 && yCoord <=1)){
                    dots.add(new Dot(xCoord, yCoord));

                }
            }
        }
    }

    /**
     * Saves a .dot file
     * @param path
     * @throws IOException
     */
    public void save(Path path) throws IOException {
        try(PrintWriter out = new PrintWriter(Files.newOutputStream(path))){
            for(Dot dot : dots){
                out.println(dot.getxCoord() + "," + dot.getyCoord());
            }
        }
    }

    /**
     * Draws dots
     * @param canvas
     */
    public void drawDots(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);

        double ratio = canvas.getHeight();
        if(ratio > canvas.getWidth()){
            ratio = canvas.getWidth();
        }
        for(Dot dot : dots){
            gc.fillOval(dot.getxCoord() * ratio - DOT_WIDTH /2,
                    Math.abs(dot.getyCoord() * ratio - canvas.getHeight()) - DOT_WIDTH /2,
                    DOT_WIDTH, DOT_WIDTH);
        }
    }

    /**
     * draws lines between the dots
     * @param canvas
     */
    public void drawLines(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        double ratio = canvas.getHeight();
        if(ratio > canvas.getWidth()){
            ratio = canvas.getWidth();
        }
        if(dots.size() > 0){

            gc.moveTo(dots.get(0).getxCoord(), dots.get(0).getyCoord());
            gc.beginPath();
            for(int i = 1; i < dots.size(); ++i){
                gc.lineTo(dots.get(i).getxCoord() * ratio,
                        Math.abs(dots.get(i).getyCoord() * ratio - canvas.getHeight()));
            }
            gc.lineTo(dots.get(0).getxCoord() * ratio,
                    Math.abs(dots.get(0).getyCoord() * ratio - canvas.getHeight()));
            gc.closePath();
            gc.stroke();
        }

    }


    /**
     * Removes dotds to the number of desired dots
     * @param numDots
     * @param strategy
     * @throws IllegalArgumentException
     */
    public void removeDots(int numDots, String strategy) throws IllegalArgumentException{
        if(numDots < 3){
            throw new IllegalArgumentException();
        }
        if(strategy.equalsIgnoreCase("Index")){
            removeDotsIndex(numDots, this.dots);
        } else {
            removeDotsIterator(numDots, this.dots);
        }

    }


    private long removeDotsIndex(int numDots, List<Dot> dots){
        long startTime = System.nanoTime();
        double lowCrit = 1;
        int indexLowCrit = 0;
        while(numDots < dots.size()){
            lowCrit = dots.get(0).calculateCriticalValue(dots.get(dots.size() - 1),
                    dots.get(1));
            indexLowCrit = 0;
            for(int i = 1; i < dots.size() - 1; ++i){
                double currentLowCrit = dots.get(i).calculateCriticalValue(dots.get(i-1),
                        dots.get(i+1));

                if(currentLowCrit < lowCrit){
                    lowCrit = currentLowCrit;
                    indexLowCrit = i;
                }

            }
            double lastLowCrit = dots.get(dots.size() - 1)
                    .calculateCriticalValue(dots.get(dots.size() - 2), dots.get(0));
            if(lastLowCrit < lowCrit){
                lowCrit = lastLowCrit;
                indexLowCrit = dots.size() - 1;
            }
            dots.remove(indexLowCrit);

        }
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000;
        System.out.println(elapsedTime);
        return elapsedTime;

    }

    private long removeDotsIterator(int numDots, Collection<Dot> collection){
        long startTime = System.nanoTime();
        double lowestCrit = 1;
        int indexOfLowCrit = 0;
        Dot lowestDot;
        while(numDots < collection.size()){
            Iterator<Dot> itrCur = collection.iterator();
            Iterator<Dot> itrPrev = collection.iterator();
            Iterator<Dot> itrNext = collection.iterator();
            Dot firstDot = itrNext.next();
            Dot secondDot = itrNext.next();
            itrCur.next();
            lowestCrit = itrCur.next().calculateCriticalValue(itrPrev.next(), itrNext.next());
            indexOfLowCrit = 1;
            lowestDot = firstDot;
            for(int i = 2; i < collection.size() - 1; ++i){
                Dot curDot = itrCur.next();
                double nextCritValue = curDot.calculateCriticalValue(itrPrev.next(),
                        itrNext.next());
                if(nextCritValue < lowestCrit){
                    lowestCrit = nextCritValue;
                    lowestDot = curDot;
                    indexOfLowCrit = i;
                }
            }
            Dot secondLastDot = itrPrev.next();
            Dot lastDot = itrPrev.next();
            double firstLowCrit = firstDot.calculateCriticalValue(lastDot, secondDot);
            if(firstLowCrit < lowestCrit){
                lowestCrit = firstLowCrit;
                lowestDot = firstDot;
                indexOfLowCrit = 0;
            }
            double lastLowCrit = lastDot.calculateCriticalValue(secondLastDot, firstDot);
            if(lastLowCrit < lowestCrit){
                lowestCrit = lastLowCrit;
                lowestDot = lastDot;
                indexOfLowCrit = collection.size() - 1;
            }
            collection.remove(lowestDot);
        }
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000;
        System.out.println(elapsedTime);
        return elapsedTime;
    }
}