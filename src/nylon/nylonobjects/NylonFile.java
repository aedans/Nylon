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

public class NylonFile extends NylonObject {
    public File file;

    public NylonFile(File file) {
        this.file = file;
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return file.exists();
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return file.getTotalSpace();
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        try {
            if (file.isDirectory()) {
                return new Iterator<NylonObject>() {
                    File[] files = file.listFiles();
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
                    Iterator<String> lines = Files.lines(file.toPath()).iterator();

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
    public NylonList toList(Stack<NylonObject> stack) {
        NylonList nylonList = new NylonList();
        this.toIterator(stack).forEachRemaining(nylonList::add);
        return nylonList;
    }

    @Override
    public String toString() {
        return file.toString();
    }

    @Override
    public NylonObject clone() {
        return new NylonFile(file);
    }
}
