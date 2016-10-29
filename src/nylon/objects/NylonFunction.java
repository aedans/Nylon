package nylon.objects;

import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonFunction implements NylonObject {

    @SuppressWarnings("unchecked")
    public NylonStack apply(NylonStack superStack) throws NylonRuntimeException {
        if (superStack.size() != 0 && superStack.peek().getClass() == FunctionSkipObject.class) {
            superStack.pop();
            return new NylonStack();
        }

        NylonStack functionStack = new NylonStack();
        this.applyImpl(superStack, functionStack);
        return functionStack;
    }

    protected abstract void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public char toChar() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Character.class);
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Integer.class);
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Double.class);
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Boolean.class);
    }

    @Override
    public NylonObject increment() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot increment a NylonFunction.");
    }

    @Override
    public NylonObject decrement() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot not decrement a NylonFunction");
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        if (nylonObject instanceof NylonFunction){
            return new NylonFunction() {
                @Override
                protected void applyImpl(NylonStack args, NylonStack returnStack)
                        throws NylonRuntimeException {
                    NylonFunction.this.applyImpl(args, returnStack);
                    returnStack.addAll(((NylonFunction) nylonObject).apply(returnStack));
                }

                @Override
                public String toString() {
                    return "ConcatenatedFunction[" + NylonFunction.this.toString() + ", " + nylonObject.toString() + "]";
                }
            };
        }
        throw new NylonRuntimeException("Cannot concatenate a NylonFunctions.");
    }

    @Override
    public NylonFunction clone() {
        try {
            return (NylonFunction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

}
