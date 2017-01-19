package nylon.builtins.objects;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.nylonobjects.NylonArray;
import nylon.nylonobjects.NylonString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonFile extends NylonObject<File> {
    public NylonFile(File file) {
        super(file, Type.FILE, "File");
    }

    @Override
    public boolean toBoolean() {
        return this.value.exists();
    }

    @Override
    public double toDouble() {
        return this.value.getTotalSpace();
    }

    @Override
    public Iterator<NylonObject> toIterator() throws NylonException {
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
            throw new NylonException(e.getMessage(), this);
        }
    }

    @Override
    public NylonArray toArray() throws NylonException {
        NylonArray nylonArray = new NylonArray();
        this.toIterator().forEachRemaining(nylonArray.value::add);
        return nylonArray;
    }

    @Override
    public NylonObject clone() {
        return new NylonFile(this.value);
    }
}
