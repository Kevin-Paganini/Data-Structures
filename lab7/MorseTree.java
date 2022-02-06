package paganiniK;


/**
 * Creates a binary tree with the root being null and uses dots
 * and dashes to determine if it should be a left or right child
 * @param <E>
 */
public class MorseTree<E> {

    private static class Node<E> {
        E value;
        Node<E> lChild;
        Node<E> rChild;

        Node(E value) {
            this(value, null, null);
        }

        Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this.value = value;
            this.lChild = leftChild;
            this.rChild = rightChild;
        }

        void set(E value) {
            this.value = value;
        }
    }

    private Node<E> root;

    /**
     * Makes sure the root is null
     */
    public MorseTree() {
        root = new Node<E>(null);
    }


    /**
     * adding a value - key pair
     * @param symbol
     * @param code
     * @throws NullPointerException
     */
    public void add(E symbol, String code) {
        if (symbol == null || code.length() == 0) {
            throw new NullPointerException("Tree does not support null.");
        }

        add(symbol, code, root);
    }

    /**
     * Recursicve add method to add in correct spot of Morse tree
     * @param symbol
     * @param code
     * @param subRoot
     * @throws IllegalArgumentException
     */
    public void add(E symbol, String code, Node<E> subRoot) {
        if(code.length() > 1) {
            if(code.startsWith(".")){
                if(subRoot.lChild == null){
                    subRoot.lChild = new Node<E>(null);
                }
                add(symbol, code.substring(1), subRoot.lChild);
            } else if(code.startsWith("-")) {
                if(subRoot.rChild == null) {
                    subRoot.rChild = new Node<E>(null);
                }
                add(symbol, code.substring(1), subRoot.rChild);
            }


        } else {
            if(code.equals(".")){
                if(subRoot.lChild == null){
                    subRoot.lChild = new Node<E>(symbol);
                } else {
                    subRoot.lChild.set(symbol);
                }
            } else if(code.equals("-")){
                if(subRoot.rChild == null) {
                    subRoot.rChild = new Node<E>(symbol);
                } else {
                    subRoot.rChild.set(symbol);
                }
            } else {
                throw new IllegalArgumentException("Not a dash or dot...");
            }
        }

    }

    /**
     * This does nothing yet but maybe Ill do it
     * @return String
     */
    public String toString(){
        return "not done yet";
    }

    /**
     * Matches symbol to code key
     * @param code
     * @return Symbol of the code
     */
    public E decode(String code) {
        E symbol = null;
        if(code.length() == 0) {
            throw new IllegalArgumentException("code cant be zero length");
        } else {
            try {
                symbol = decode(code, root);
            } catch(NullPointerException e){
                System.out.println("does not support null arguments");
            }
        }
        return symbol;
    }

    /**
     * Recursive decode to match symbol and code
     * @param code
     * @param root
     * @return Symbol of the code
     */
    private E decode(String code, Node<E> root) {
        E symbol;
        if (code.length() > 0) {
            if (code.startsWith(".")) {
                symbol = decode(code.substring(1), root.lChild);
            } else {
                symbol = decode(code.substring(1), root.rChild);
            }


        } else {
            symbol = root.value;
        }
        return symbol;
    }



}