/*
 *  Course: CS1011-051
 *  Fall 2020-2021
 *  File header contains class TheSuperTester9000
 *  Name: paganinik
 *  Created 4/20/2021
 */
package paganiniK;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Course CS1011-051
 * Fall 2020-2021
 * TheSuperTester9000 purpose: Test everything
 *
 * @author paganinik
 * @version created on 4/20/2021 at 8:59 PM
 */
public class TheSuperTester9000 {

    private SmallerBiggerSort sort = new SmallerBiggerSort();

    private static final ArrayList<Integer> justOne = new ArrayList<>();
    private static final ArrayList<Integer> justTwo = new ArrayList<>();
    private static final ArrayList<Integer> justThree = new ArrayList<>();
    private static final ArrayList<Integer> justFour = new ArrayList<>();
    private static final ArrayList<Integer> justFive = new ArrayList<>();


    @BeforeEach
    void setup(){

        System.out.println("hi there");


    }

    @Test
    void justOneTest() {
        Random rand = new Random();
        for( int i = 0; i < 1000; ++i) {
            justOne.add(rand.nextInt() / 10000);
        }

        ArrayList<Integer> check = new ArrayList();

        for(int i = 0; i < justOne.size(); ++i){
            check.add(i, justOne.get(i));
        }

        Collections.sort(check);
        Object[] checkCopy = check.toArray();

        long startTime = System.nanoTime();

        sort.sort(justOne);

        long endTime = System.nanoTime();
        System.out.println("Fully Sorted List:");
        System.out.println("Time to sort list: " + (endTime - startTime) / 1000.0);
        Object[] copyOfOrig = justOne.toArray();

        for(int i =0; i < justOne.size(); ++i){
            System.out.println(justOne.get(i));
            System.out.println(check.get(i));
            System.out.println();
        }
        assertArrayEquals(copyOfOrig, checkCopy);
    }

    @Test
    void justTwoTest() {
        justTwo.add(8);
        justTwo.add(100);
        justTwo.add(73);
        justTwo.add(1);
        justTwo.add(12);
        justTwo.add(20);
        justTwo.add(87);
        justTwo.add(1);
        justTwo.add(9);




        ArrayList<Integer> check = new ArrayList();

        for(int i = 0; i < justTwo.size(); ++i){
            check.add(i, justTwo.get(i));
        }

        Collections.sort(check);
        Object[] checkCopy = check.toArray();
        long startTime = System.nanoTime();

        sort.sort(justTwo);

        long endTime = System.nanoTime();
        System.out.println("Fully Sorted List:");
        System.out.println("Time to sort list: " + (endTime - startTime) / 1000.0);
        Object[] copyOfOrig = justTwo.toArray();

        for(int i =0; i < justTwo.size(); ++i){
            System.out.println(justTwo.get(i));
            System.out.println(check.get(i));
            System.out.println();
        }
        assertArrayEquals(copyOfOrig, checkCopy);
    }

    @Test
    void justThreeTest() {
        int iter = 1;
        for( int i = 0; i < 1000; ++i) {
            justThree.add(iter++);
        }


        ArrayList<Integer> check = new ArrayList();

        for(int i = 0; i < justThree.size(); ++i){
            check.add(i, justThree.get(i));
        }


        Collections.sort(check);


        Object[] checkCopy = check.toArray();

        long startTime = System.nanoTime();

        sort.sort(justThree);

        long endTime = System.nanoTime();
        System.out.println("Fully Sorted List:");
        System.out.println("Time to sort list: " + (endTime - startTime) / 1000.0);

        Object[] copyOfOrig = justThree.toArray();

        for(int i =0; i < justThree.size(); ++i){
            System.out.println(justThree.get(i));
            System.out.println(check.get(i));
            System.out.println();
        }
        assertArrayEquals(copyOfOrig, checkCopy);

    }

    @Test
    void justFourTest() {



        ArrayList<Integer> check = new ArrayList();

        for(int i = 0; i < justFour.size(); ++i){
            check.add(i, justFour.get(i));
        }

        Collections.sort(check);
        Object[] checkCopy = check.toArray();
        long startTime = System.nanoTime();

        sort.sort(justFour);

        long endTime = System.nanoTime();
        System.out.println("Null List");
        System.out.println("Time to sort list: " + (endTime - startTime) / 1000);
        Object[] copyOfOrig = justFour.toArray();

        for(int i =0; i < justFour.size(); ++i){
            System.out.println(justFour.get(i));
            System.out.println(check.get(i));
            System.out.println();
        }
        assertArrayEquals(copyOfOrig, checkCopy);
    }

    @Test
    void justFiveTest() {
        justFive.add(1);
        justFive.add(1);
        justFive.add(1);
        justFive.add(1);






        ArrayList<Integer> check = new ArrayList();

        for(int i = 0; i < justFive.size(); ++i){
            check.add(i, justFive.get(i));
        }

        Collections.sort(check);
        Object[] checkCopy = check.toArray();
        long startTime = System.nanoTime();

        sort.sort(justFive);

        long endTime = System.nanoTime();
        System.out.println("Fully Sorted List:");
        System.out.println("Time to sort list: " + (endTime - startTime) / 1000);
        Object[] copyOfOrig = justFive.toArray();

        for(int i =0; i < justFive.size(); ++i){
            System.out.println(justFive.get(i));
            System.out.println(check.get(i));
            System.out.println();
        }
        assertArrayEquals(copyOfOrig, checkCopy);
    }



}
