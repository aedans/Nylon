package nylon.nylonobjects;

import nylon.NylonException;
import nylon.NylonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class NylonFile extends NylonObject<File> {
    public NylonFile(File file) {
        super(file, Type.FILE);
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return this.value.exists();
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.value.getTotalSpace();
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        try {
            if (this.value.isDirectory()) {
                return new Iterator<NylonObject>() {
                    File[] files = NylonFile.this.value.listFiles();
                    int i = 0;

                    @Override
                    public boolean hasNext() {
                        return i < files.length;
                    }

                    @Override
                    public NylonObject next() {
                        return new NylonFile(files[i++]) {
                            @Override
                            public String toString() {
                                return super.toString();
                            }
                        };
                    }
                };
            } else {
                return new Iterator<NylonObject>() {
                    Iterator<String> lines = Files.lines(NylonFile.this.value.toPath()).iterator();

                    @Override
                    public boolean hasNext() {
                        return lines.hasNext();
                    }

                    @Override
                    public NylonObject next() {
                        return new NylonString((lines.next()).toCharArray());
                    }
                };
            }
        } catch (IOException e) {
            throw new NylonException(e.getMessage());
        }
    }

    @Override
    public NylonArray toArray(Stack<NylonObject> stack) {
        NylonArray nylonArray = new NylonArray();
        this.toIterator(stack).forEachRemaining(nylonArray::add);
        return nylonArray;
    }

    @Override
    public NylonObject clone() {
        return new NylonFile(this.value);
    }
}
