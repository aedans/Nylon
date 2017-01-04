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

    public void apply(Stack<NylonObject> stack) throws NylonException {
        try {
            applyImpl(stack);
        } catch (NylonException e) {
            e.add(this);
            throw e;
        } catch (Exception e) {
            throw new NylonException(e.getClass().getCanonicalName() + ": " + e.getMessage(), this);
        }
    }

    public abstract void applyImpl(Stack<NylonObject> stack) throws NylonException;

    @Override
    public double toDouble(Stack<NylonObject> stack) throws NylonException {
        throw new NylonException("Cannot cast function to non-function objects", this);
    }

    @Override
    public NylonFunction toFunction(Stack<NylonObject> stack) {
        return this;
    }

    @Override
    public NylonFunction concatenate(NylonObject object, Stack<NylonObject> stack) {
        return new InlineFunction("ConcatenatedFunction(" + this.id + ", " + object.getId() + ")",
                this, object.toFunction(stack));
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        throw new NylonException("Cannot subtract functions", this);
    }

    @Override
    public NylonFunction clone() throws CloneNotSupportedException {
        return new NylonFunction() {
            {
                id = NylonFunction.this.id;
            }

            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                NylonFunction.this.applyImpl(stack);
            }

            @Override
            public String toString() {
                return NylonFunction.this.toString();
            }
        };
    }
}
