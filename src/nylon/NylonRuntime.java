package nylon;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.NylonSrcFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonString;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {

    @SuppressWarnings("unchecked")
    private LinkedList<NylonObject>[] stacks = new LinkedList[52];

    private ArrayList<NylonSrcFunction> nylonFunctions = new ArrayList<>();

    public NylonRuntime(String src) throws NylonRuntimeException {
        for (String s : src.split("\n")){
            if (s.startsWith("!")){
                for (String a : s.split(" ")){
                    src = src.replace(String.valueOf(a.charAt(0)), a.substring(1));
                }
                src = src.replace(s, "");
            }
        }
        for (String s : src.split("\n")){
            this.nylonFunctions.add(new NylonSrcFunction(this, s));
        }
        for (int i = 0; i < 52; i++) {
            this.stacks[i] = new LinkedList<>();
        }
    }

    public void addInput(String arg) {
        stacks[0].add(new NylonString(arg));
    }

    @Override
    public void run() {
        try {
            this.getFunction(0).apply(new LinkedList<>()).forEach(System.out::println);
        } catch (NylonRuntimeException e){
            e.printStackTrace(System.out);
        } catch (Exception e){
            System.out.println("Unrecognized Exception: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public NylonSrcFunction getFunction(int function) throws NylonRuntimeException {
        if (function >= nylonFunctions.size())
            throw new NylonRuntimeException("Could not access function " + function);
        return nylonFunctions.get(function);
    }

    public LinkedList<NylonObject> getStack(int stack) throws NylonRuntimeException {
        if (stack >= 52)
            throw new NylonRuntimeException("Could not access stack " + stack);
        return stacks[stack];
    }

}
