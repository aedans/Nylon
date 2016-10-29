package nylon;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.FunctionDictionary;
import nylon.functions.NylonSrcFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;
import nylon.objects.NylonString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {

    private NylonStack nylonStack = new NylonStack();

    private ArrayList<NylonSrcFunction> nylonFunctions = new ArrayList<>();

    private FunctionDictionary functionDictionary = new FunctionDictionary(this);

    private File library;

    public NylonRuntime(String src, File library) throws NylonRuntimeException {
        this.library = library;
        for (String s : src.split("\n")){
            if (s.startsWith("=")){
                for (String a : s.split(" ")){
                    src = src.replace(String.valueOf(a.charAt(0)), a.substring(1));
                }
                src = src.replace(s, "");
            }
        }
        String[] functions = src.split("\n");
        this.nylonFunctions.add(new NylonSrcFunction(this, functions[0]));
        for (int i = 1; i < functions.length; i++) {
            this.nylonFunctions.add(new NylonSrcFunction(this, functions[i]));
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

    public NylonSrcFunction getFunction(String function) throws NylonRuntimeException {
        try {
            for (File f : library.listFiles()) {
                if (Objects.equals(f.getName(), function)) {
                    final String[] content = {""};
                    new BufferedReader(new FileReader(f)).lines().forEach(s -> content[0] += s + '\n');
                    return new NylonSrcFunction(this, content[0]);
                }
            }
            throw new NylonRuntimeException("Could not find function with name \"" + function + "\"");
        } catch (Exception e){
            throw new NylonRuntimeException(e.getMessage());
        }
    }

}
