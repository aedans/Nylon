package nylon;

import nylon.exceptions.NylonRuntimeException;
import nylon.functions.FunctionDictionary;
import nylon.functions.InlineFunction;
import nylon.objects.NylonObject;
import nylon.objects.NylonStack;
import nylon.objects.NylonString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Aedan Smith.
 */

public class NylonRuntime implements Runnable {

    private NylonStack args = new NylonStack();

    private InlineFunction main;

    private FunctionDictionary functionDictionary = new FunctionDictionary(this);

    private File library;

    private HashMap<String, NylonObject> variables = new HashMap<>();

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
        this.main = new InlineFunction(this, src);
    }

    public void addInput(String arg) {
        args.add(new NylonString(arg));
    }

    @Override
    public void run() {
        try {
            this.main.apply(args).forEach(System.out::println);
        } catch (NylonRuntimeException e){
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        } catch (Exception e){
            System.out.println("Unrecognized Exception: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public void addVariable(String name, NylonObject object) {
        this.variables.put(name, object);
    }

    public NylonObject getVariable(String s) throws NylonRuntimeException {
        NylonObject object = variables.get(s);
        if (object != null) {
            return variables.get(s);
        } else {
            try {
                return getLibraryFunction(s);
            } catch (NylonRuntimeException e) {
                throw new NylonRuntimeException("Could not find variable with name \"" + s + "\"");
            }
        }
    }

    public InlineFunction getLibraryFunction(String function) throws NylonRuntimeException {
        try {
            for (File f : library.listFiles()) {
                if (Objects.equals(f.getName(), function)) {
                    final String[] content = {""};
                    new BufferedReader(new FileReader(f)).lines().forEach(s -> content[0] += s + '\n');
                    return new InlineFunction(this, content[0]);
                }
            }
            throw new NylonRuntimeException("Could not find function with name \"" + function + "\"");
        } catch (Exception e){
            throw new NylonRuntimeException(e.getMessage());
        }
    }

    public FunctionDictionary getFunctionDictionary() {
        return functionDictionary;
    }

}
