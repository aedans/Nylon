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
    public NylonFunction(String id) {
        super(null, Type.FUNCTION, id);
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
        } catch (Throwable e) {
            throw new NylonException(e.getClass().getCanonicalName() + ": " + e.getMessage(), this);
        }
    }

    protected abstract void applyImpl(Stack<NylonObject> stack) throws NylonException;

    public Stack<NylonObject> toStack() throws NylonException {
        Stack<NylonObject> stack = new Stack<>();
        this.apply(stack);
        return stack;
    }

    @Override
    public double toDouble() throws NylonException {
        return toStack().peek().toDouble();
    }

    @Override
    public long toLong() throws NylonException {
        return toStack().peek().toLong();
    }

    @Override
    public char toCharacter() throws NylonException {
        return toStack().peek().toCharacter();
    }

    @Override
    public boolean toBoolean() throws NylonException {
        return toStack().peek().toBoolean();
    }

    @Override
    public NylonArray toArray() throws NylonException {
        return new NylonArray(toStack());
    }

    @Override
    public NylonString toNylonString() throws NylonException {
        return toStack().peek().toNylonString();
    }

    @Override
    public NylonFunction toFunction() {
        return this;
    }

    @Override
    public NylonFunction concatenate(NylonObject object) {
        return new InlineFunction("ConcatenatedFunction(" + this.id + ", " + object.getId() + ")",
                this, object.toFunction());
    }

    @Override
    public NylonObject subtract(NylonObject object) throws NylonException {
        throw new NylonException("Cannot subtract functions", this);
    }

    @Override
    public NylonFunction clone() throws CloneNotSupportedException {
        return new NylonFunction(id) {
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
