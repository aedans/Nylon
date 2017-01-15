package nylon.parser;

import nylon.Builtins;
import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.parsers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class NylonParser {
    public ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>> parsers = new ArrayList<>(256);

    public HashMap<String, Supplier<NylonFunction>> functions = new HashMap<>();

    public NylonParser(File stdl) {
        this(
                stdl,
                DefaultParser::addTo,
                CaptureParser::addTo,
                CastParser::addTo,
                CharacterParser::addTo,
                ExternalFunctionParser::addTo,
                ForLoopParser::addTo,
                IfStatementParser::addTo,
                MacroParser::addTo,
                NumberParser::addTo,
                NylonFunctionParser::addTo,
                StringParser::addTo,
                WhileNotEmptyLoop::addTo
        );
    }

    @SafeVarargs
    public NylonParser(File stdl, Consumer<ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>>>... parsers) {
        for (Consumer<ArrayList<BiFunction<StringIterator, NylonParser, NylonFunction>>> parser : parsers) {
            parser.accept(this.parsers);
        }
        Builtins.build(this, stdl);
    }

    public void parse(StringIterator in, InlineFunction inlineFunction) {
        while (in.hasNext()) {
            NylonFunction parse = parse(in);
            if (parse != null) {
                inlineFunction.functions.add(parse);
            }
        }
    }

    public NylonFunction parse(StringIterator in) {
        NylonFunction nylonFunction = null;
        while (in.hasNext() && nylonFunction == null) {
            if (in.hasNext(2) && Objects.equals(in.peekString(2), "//"))
                in.until('\n');
            nylonFunction = parsers.get(in.peek()).apply(in, this);
        }
        return nylonFunction;
    }

    public void parseUntil(InlineFunction inlineFunction, StringIterator in, char c) {
        loop:
        while (in.hasNext()) {
            NylonFunction nylonFunction = null;
            while (in.hasNext() && nylonFunction == null) {
                if (in.hasNext(2) && Objects.equals(in.peekString(2), "//"))
                    in.until('\n');
                if (in.peek() == c)
                    break loop;
                nylonFunction = parsers.get(in.peek()).apply(in, this);
            }
            if (nylonFunction != null) {
                inlineFunction.functions.add(nylonFunction);
            }
        }
    }
}
