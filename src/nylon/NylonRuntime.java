package nylon;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.FunctionDictionary;
import nylon.functions.NylonSrcFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;
import nylon.objects.NylonString;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {

    private NylonStack nylonStack = new NylonStack();

    private ArrayList<NylonSrcFunction> nylonFunctions = new ArrayList<>();

    private FunctionDictionary functionDictionary = new FunctionDictionary();

    public NylonRuntime(String src) throws NylonRuntimeException {
        for (String s : src.split("\n")){
            if (s.startsWith("=")){
                for (String a : s.split(" ")){
                    src = src.replace(String.valueOf(a.charAt(0)), a.substring(1));
                }
                src = src.replace(s, "");
            }
        }
        for (String s : src.split("\n")){
            this.nylonFunctions.add(new NylonSrcFunction(this, s, 0));
        }
    }

    public void addInput(String arg) {
        nylonStack.add(new NylonString(arg));
    }

    @Override
    public void run() {
        try {
            this.getFunction(0).apply(nylonStack).forEach(System.out::println);
        } catch (NylonRuntimeException e){
            e.printStackTrace(System.out);
        } catch (Exception e){
            System.out.println("Unrecognized Exception: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public FunctionDictionary getFunctionDictionary() {
        return functionDictionary;
    }

    public NylonSrcFunction getFunction(int function) throws NylonRuntimeException {
        if (function >= nylonFunctions.size())
            throw new NylonRuntimeException("Could not access function " + function);
        return nylonFunctions.get(function);
    }

}
