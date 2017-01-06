package nylon;

import nylon.nylonobjects.*;

import java.io.File;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 *
 * Class for handling Objects on the Nylon stack.
 */

public abstract class NylonObject<T> {
    public T value;
    public boolean shouldOutputNewline = true;
    protected String id;
    protected Type type;

    public NylonObject(T value, Type type, String id) {
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public abstract double toDouble(Stack<NylonObject> stack) throws NylonException;

    public boolean toBoolean(Stack<NylonObject> stack) throws NylonException {
        return toDouble(stack) != 0;
    }

    public long toLong(Stack<NylonObject> stack) throws NylonException {
        return (long) toDouble(stack);
    }

    public char toCharacter(Stack<NylonObject> stack) throws NylonException {
        return (char) toDouble(stack);
    }

    public NylonArray toArray(Stack<NylonObject> stack) throws NylonException {
        return new NylonArray(this);
    }

    public NylonFunction toFunction(Stack<NylonObject> stack) {
        return new NylonFunction("Push" + NylonObject.this.getClass().getSimpleName() + "(" + NylonObject.this + ")") {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                try {
                    stack.add(NylonObject.this.clone());
                } catch (CloneNotSupportedException e) {
                    throw new NylonException("Could not clone object \"" + this.id + "\"", this);
                }
            }

            @Override
            public String toString() {
                return id;
            }
        };
    }

    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) throws NylonException {
        return new Iterator<NylonObject>() {
            long i = 0, number = toLong(stack);

            @Override
            public boolean hasNext() {
                return i < number;
            }

            @Override
            public NylonObject next() {
                return new NylonLong(i++);
            }
        };
    }

    public NylonString toNylonString(Stack<NylonObject> stack) {
        return new NylonString(this.toString().toCharArray());
    }

    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        return new NylonDouble(this.toDouble(stack) + object.toDouble(stack));
    }

    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        return new NylonDouble(this.toDouble(stack) - object.toDouble(stack));
    }

    public NylonObject multiply(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        return new NylonDouble(this.toDouble(stack) * object.toDouble(stack));
    }

    public NylonObject divide(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        return new NylonDouble(this.toDouble(stack) / object.toDouble(stack));
    }

    public NylonObject to(Type type, Stack<NylonObject> stack) throws NylonException {
        switch (type) {
            case BOOL:
                return new NylonBoolean(this.toBoolean(stack));
            case CHAR:
                return new NylonCharacter(this.toCharacter(stack));
            case LONG:
                return new NylonLong(this.toLong(stack));
            case DOUBLE:
                return new NylonDouble(this.toDouble(stack));
            case ARRAY:
                return this.toArray(stack);
            case STRING:
                return this.toNylonString(stack);
            case FUNCTION:
                return this.toFunction(stack);
            case FILE:
                return new NylonFile(new File(this.toString()));
            default:
                throw new NylonException("Could not cast object to type " + type, this);
        }
    }

    public NylonObject promote(NylonObject object, Stack<NylonObject> stack) throws NylonException {
        if (this.type.promotionLevel < object.type.promotionLevel) {
            return this.to(object.type, stack);
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NylonObject && this.value.equals(((NylonObject) obj).value);
    }

    @Override
    public NylonObject clone() throws CloneNotSupportedException {
        return (NylonObject) super.clone();
    }

    public String getId() {
        return id + '(' + value + ')';
    }

    public Type getType() {
        return type;
    }

    protected enum Type {
        FILE((byte) -1),
        BOOL((byte) 0),
        LONG((byte) 1),
        CHAR((byte) 2),
        DOUBLE((byte) 3),
        STRING((byte) 4),
        ARRAY((byte) 5),
        FUNCTION((byte) 6);

        protected byte promotionLevel;

        Type(byte promotionLevel) {
            this.promotionLevel = promotionLevel;
        }
    }
}
