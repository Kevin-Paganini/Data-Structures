package paganiniK;


import java.util.EmptyStackException;

/**
 * Stack ADT that stores ints
 * @param <E>
 */
public class IntStack<E> {
    private Node head;
    private int size;

    /**
     * Data holder
     * and next Node
     * for the stack
     * its a block in the chain
     */

    private static class Node {

        int data;
        Node next;
    }


    /**
     * Constructor for the stack
     */
    public IntStack(){
        this.head = null;
    }


    /**
     * Add value to stack
     * @param value
     */
    public void push(int value){

        Node temp = new Node();


        if (temp == null) {
            System.out.print("Stack overflow");
            return;
        }


        temp.data = value;


        temp.next = head;


        head = temp;
        size++;

    }

    /**
     * pop elements off stack
     * @return popped integer
     */
    public int pop(){
        int data;
        if (!isEmpty()){
            data = head.data;
            head = (head).next;
            size--;

        } else {
            System.out.println("The stack is empty...");
            throw new EmptyStackException();
        }
        return data;
    }

    /**
     * peeking at next value
     * @return next element in stack without removing it
     * @throws EmptyStackException
     */
    public int peek(){

        if (!isEmpty()) {
            return head.data;
        } else {
            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
    }

    /**
     * Checks if stack is empty
     * @return true or false
     */
    public boolean isEmpty(){

        return head == null;
    }

    /**
     * Shows size of stack
     * @return int
     */
    public int size(){
        return size;
    }

    /**
     * String representation of stack
     * @return string
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("|          |\n");
        s.append("|----------|\n");
        Node temp = head;
        while(temp != null){
            if(temp.data / 10.0 <= 1){
                s.append("|        " + temp.data + " |\n");
            } else if(temp.data / 100.0 <= 1){
                s.append("|       " + temp.data + " |\n");
            } else if(temp.data / 1000.0 <= 1){
                s.append("|      " + temp.data + " |\n");
            } else if(temp.data / 10000.0 <= 1){
                s.append("|     " + temp.data + " |\n");
            } else if(temp.data / 100000.0 <= 1){
                s.append("|    " + temp.data + " |\n");
            } else if(temp.data / 1000000.0 <= 1){
                s.append("|  " + temp.data + " |\n");
            } else if(temp.data / 10000000.0 <= 1){
                s.append("| " + temp.data + " |\n");
            } else if(temp.data / 100000000.0 <= 1){
                s.append("|" + temp.data + " |\n");
            }

            temp = temp.next;
        }
        s.append("+----------+");
        String toString = s.toString();
        return toString;


    }

}