package nylon.nylonobjects;

import nylon.NylonObject;
import nylon.parser.NylonParser;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Aedan Smith.
 */

public class NylonList extends NylonObject implements Collection<NylonObject> {
    protected Vector<NylonObject> vector = new Vector<>();

    public NylonList() {
    }

    public NylonList(NylonObject object) {
        vector.add(object);
    }

    public NylonList(Collection<NylonObject> stack) {
        vector.addAll(stack);
    }

    @Override
    public int size() {
        return vector.size();
    }

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return vector.contains(o);
    }

    @Override
    public Iterator<NylonObject> iterator() {
        return vector.iterator();
    }

    @Override
    public Object[] toArray() {
        return vector.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return vector.toArray(a);
    }

    @Override
    public boolean add(NylonObject nylonObject) {
        return vector.add(nylonObject);
    }

    @Override
    public boolean remove(Object o) {
        return vector.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return vector.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends NylonObject> c) {
        return vector.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return vector.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return vector.retainAll(c);
    }

    @Override
    public void clear() {
        vector.clear();
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return isEmpty();
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return size();
    }

    @Override
    public NylonObject toFunction(Stack<NylonObject> stack) {
        return NylonParser.parse(this.toString());
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return iterator();
    }

    @Override
    public NylonList toList(Stack<NylonObject> stack) {
        return this;
    }

    @Override
    public NylonObject toNylonString(Stack<NylonObject> stack) {
        String s = "";
        for (NylonObject nylonObject : this) {
            s += nylonObject.toString();
        }
        return new NylonString(s.toCharArray());
    }

    @Override
    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        this.addAll(object.toList(stack));
        return this;
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        this.removeAll(object.toList(stack));
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        for (NylonObject nylonObject : this) {
            s += nylonObject + "\n";
        }
        return s;
    }

    @Override
    public NylonObject clone() {
        return new NylonList((Collection<NylonObject>) vector.clone());
    }
}
