package nylon.nylonobjects;

import nylon.InlineFunction;
import nylon.NylonException;
import nylon.NylonObject;

import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("unchecked")
public abstract class NylonFunction extends NylonObject<NylonFunction> {
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

    public abstract void apply(Stack<NylonObject> stack);

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        throw new NylonException("Cannot cast function to non-function objects");
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
