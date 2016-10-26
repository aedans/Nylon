package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonFunction implements NylonObject {

    private int args;

    public NylonFunction(int args){
        this.args = args;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<NylonObject> apply(LinkedList<NylonObject> superStack) throws NylonRuntimeException {
        if (superStack.size() != 0 && superStack.getLast().getClass() == FunctionSkipObject.class) {
            superStack.removeLast();
            return new LinkedList<>();
        }

        LinkedList<NylonObject> functionStack = new LinkedList<>();
        if (args >= 0) {
            if (superStack.size() >= args) {
                LinkedList<NylonObject> args = new LinkedList<>();
                for (int i = 0; i < this.args; i++) {
                    args.add(superStack.removeLast());
                }
                this.applyImpl(args, functionStack);
            } else {
                this.applyImpl((LinkedList<NylonObject>) superStack.clone(), functionStack);
                superStack.clear();
            }
        } else if (args == -1) {
            this.applyImpl((LinkedList<NylonObject>) superStack.clone(), functionStack);
            superStack.clear();
        } else {
            this.applyImpl(superStack, functionStack);
        }

        LinkedList<NylonObject> returnStack = new LinkedList<>();
        if (functionStack.size() != 0) {
            Class clazz = functionStack.getLast().getClass();
            NylonObject object;
            while (functionStack.size() != 0) {
                object = functionStack.removeLast();
                if (object.getClass() == clazz)
                    returnStack.addFirst(object);
                else
                    break;
            }
        }
        return returnStack;
    }

    protected abstract void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
            throws NylonRuntimeException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot convert a NylonFunction to a long.");
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot convert a NylonFunction to a long.");
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        throw new NylonRuntimeException("Cannot convert a NylonFunction to a boolean.");
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
            return new NylonFunction(args) {
                @Override
                protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
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
        return new NylonFunction(args) {
            @Override
            protected void applyImpl(LinkedList<NylonObject> args, LinkedList<NylonObject> returnStack)
                    throws NylonRuntimeException {
                NylonFunction.this.applyImpl(args, returnStack);
            }

            @Override
            public String toString() {
                return NylonFunction.this.toString();
            }
        };
    }

}
