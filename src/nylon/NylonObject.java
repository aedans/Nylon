package nylon;

import nylon.builtins.objects.NylonFile;
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

    public abstract double toDouble() throws NylonException;

    public boolean toBoolean() throws NylonException {
        return toDouble() != 0;
    }

    public long toLong() throws NylonException {
        return (long) toDouble();
    }

    public char toCharacter() throws NylonException {
        return (char) toDouble();
    }

    public NylonArray toArray() throws NylonException {
        return new NylonArray(this);
    }

    public NylonFunction toFunction() {
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

    public Iterator<NylonObject> toIterator() throws NylonException {
        return toArray().toIterator();
    }

    public NylonString toNylonString() throws NylonException {
        return new NylonString(this.toString().toCharArray());
    }

    public NylonObject concatenate(NylonObject object) throws NylonException {
        return new NylonDouble(this.toDouble() + object.toDouble());
    }

    public NylonObject subtract(NylonObject object) throws NylonException {
        return new NylonDouble(this.toDouble() - object.toDouble());
    }

    public NylonObject multiply(NylonObject object) throws NylonException {
        return new NylonDouble(this.toDouble() * object.toDouble());
    }

    public NylonObject divide(NylonObject object) throws NylonException {
        return new NylonDouble(this.toDouble() / object.toDouble());
    }

    public NylonObject to(Type type) throws NylonException {
        switch (type) {
            case BOOL:
                return new NylonBoolean(this.toBoolean());
            case CHAR:
                return new NylonCharacter(this.toCharacter());
            case LONG:
                return new NylonLong(this.toLong());
            case DOUBLE:
                return new NylonDouble(this.toDouble());
            case ARRAY:
                return this.toArray();
            case STRING:
                return this.toNylonString();
            case FUNCTION:
                return this.toFunction();
            case FILE:
                return new NylonFile(new File(this.toString()));
            default:
                throw new NylonException("Could not cast object to type " + type, this);
        }
    }

    public NylonObject promote(NylonObject object) throws NylonException {
        if (this.type.promotionLevel < object.type.promotionLevel) {
            return this.to(object.type);
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
