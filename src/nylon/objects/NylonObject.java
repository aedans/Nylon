package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public interface NylonObject extends Cloneable<NylonObject>, java.lang.Cloneable {

    @Override
    String toString();

    char toChar() throws NylonRuntimeException;

    int toInteger() throws NylonRuntimeException;

    double toDouble() throws NylonRuntimeException;

    boolean toBoolean() throws NylonRuntimeException;

    NylonObject increment() throws NylonRuntimeException;

    NylonObject decrement() throws NylonRuntimeException;

    NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException;

    default NylonObject cast(char c) throws NylonRuntimeException {
        switch (c){
            case 'c':
                return new NylonCharacter(this.toChar());
            case 'i':
                return new NylonDouble(this.toInteger());
            case 'd':
                return new NylonDouble(this.toDouble());
            case 'b':
                return new NylonCharacter(this.toBoolean() ? 't' : 'f');
            case 's':
                return new NylonString(this.toString());
            default:
                throw new NylonRuntimeException("Could not cast: No class found with identifier " + c + ".");
        }
    }

}
