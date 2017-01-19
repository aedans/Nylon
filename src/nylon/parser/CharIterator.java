package nylon.parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 */

public class CharIterator implements Iterator<Character> {
    protected int i = 0;
    protected char[] string;

    public CharIterator(String string) {
        this.string = string.toCharArray();
    }

    @Override
    public boolean hasNext() {
        return i < string.length;
    }

    public boolean hasNext(int n) {
        return i + n < string.length;
    }

    public void skip() {
        this.i++;
    }

    public void skip(int i) {
        this.i += i;
    }

    public String until(char c) {
        String s = "";
        while (hasNext() && peek() != c) {
            s += string[i++];
        }
        return s;
    }

    public String until(Predicate<CharIterator> test) {
        String s = "";
        while (test.test(this)) {
            s += next();
        }
        return s;
    }

    @Override
    public Character next() {
        if (i >= string.length)
            throw new RuntimeException("Unexpected end of string");
        return string[i++];
    }

    public Character peek() {
        if (i >= string.length)
            throw new RuntimeException("Unexpected end of string");
        return string[i];
    }

    public Character peek(int n) {
        if (i + n >= string.length)
            throw new RuntimeException("Unexpected end of string");
        return string[i + n];
    }

    public String peekString(int n) {
        if (i + n >= string.length)
            throw new RuntimeException("Unexpected end of string");
        return new String(Arrays.copyOfRange(string, i, i + n));
    }

    public boolean isInRange(char low, char high) {
        if (i >= string.length)
            throw new RuntimeException("Unexpected end of string");
        return string[i] >= low && string[i] <= high;
    }

    public void end() {
        i = string.length;
    }

    public char[] getString() {
        return string;
    }

    public int getIndex() {
        return i;
    }

    public void skipWhitespace() {
        while (hasNext() && peek() <= 32)
            skip();
    }
}
