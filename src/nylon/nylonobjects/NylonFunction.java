package nylon.nylonobjects;

import nylon.InlineFunction;
import nylon.NylonException;
import nylon.NylonObject;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("unchecked")
public abstract class NylonFunction extends NylonObject<Function<Stack<NylonObject>, NylonObject>>
        implements Function<Stack<NylonObject>, NylonObject> {
    public NylonFunction() {
        super(null, Type.FUNCTION);
        this.value = this;
    }

    public static String format(String s) {
        String v = "";
        int tDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                v += s.charAt(i);
                tDepth++;
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
            } else if (s.charAt(i) == ']') {
                tDepth--;
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
                v += s.charAt(i);
            } else if (s.charAt(i) == ',') {
                v += s.charAt(i);
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
                i++;
            } else if (s.charAt(i) != '\n' && s.charAt(i) != '\t') {
                v += s.charAt(i);
            }
        }
        return v;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toBoolean(stack);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toDouble(stack);
    }

    @Override
    public long toLong(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toLong(stack);
    }

    @Override
    public char toCharacter(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toCharacter(stack);
    }

    @Override
    public NylonArray toArray(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toArray(stack);
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return this.value.apply((Stack<NylonObject>) stack.clone()).toIterator(stack);
    }

    @Override
    public NylonFunction toFunction(Stack<NylonObject> stack) {
        return this;
    }

    @Override
    public NylonFunction concatenate(NylonObject object, Stack<NylonObject> stack) {
        return new InlineFunction(this, object.toFunction(stack));
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        throw new NylonException("Cannot subtract functions");
    }
}
