package paganiniK;


import java.util.EmptyStackException;

/**
 * Method call mimicking computer stack
 */
public class ProgramStack {

    private IntStack stack;

    /**
     * Constructor
     */
    public ProgramStack(){
        stack = new IntStack();

    }

    /**
     * Calls all methods
     * @param name
     * @param arguments
     */
    public void callMethod(String name, int... arguments){
        int numArguments = 1;




        stack.push(methodToProgramCounter(name, arguments));
        if(arguments.length != 0 || arguments != null){
            for(int i = 0; i < arguments.length; ++i){
                stack.push(arguments[i]);
                numArguments++;
            }
        }

        stack.push(numArguments);
    }

    /**
     * return from method
     * removes last stack frame
     * @throws EmptyStackException
     */
    public void returnFromMethod(){

        try{
            int numTimes = stack.pop();
            for(int i = 0; i < numTimes; ++i){
                stack.pop();
            }



        } catch (EmptyStackException e){
            System.out.println("Stack is empty... ");
            throw new EmptyStackException();
        }
    }


    /**
     * removes stack frame and returns value
     * @param returnValue
     */
    public void returnFromMethod(int returnValue){
        returnFromMethod();
        int top = stack.pop();
        stack.push(returnValue);
        top++;
        stack.push(top);
    }

    /**
     * Turns method name and arguments into a integer
     * @param name
     * @param arguments
     * @return integer
     */
    private int methodToProgramCounter(String name, int... arguments){
        if(arguments == null){
            System.out.println("arguments has not been initialised");
        }
        int value = 0;
        int numArguments;
        if(arguments.length != 0){
            numArguments = 1;
        } else {
            numArguments = 0;
        }
        char[] nameChar = name.toCharArray();
        value = nameChar[0] * 2;

        for(int i = 1; i < name.length(); ++i){
            value += nameChar[i];
            value *= 2;

        }

        value += numArguments;


        return value;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}