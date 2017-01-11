package nylon.parser;

import nylon.Builtins;
import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.parsers.*;
import parser.LinkedParser;
import parser.ParseException;
import parser.StringIterator;

import java.io.File;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Aedan Smith.
 */

public class NylonParser extends LinkedParser<StringIterator, InlineFunction> {
    public HashMap<String, Supplier<NylonFunction>> functions = new HashMap<>();

    public NylonParser(File stdl) {
        super(new DefaultParser());
        parsers.add(new CharacterParser());
        parsers.add(new StringParser());
        parsers.add(new NylonFunctionParser(this));
        parsers.add(new CastParser());
        parsers.add(new WhileNotEmptyLoop(this));
        parsers.add(new CaptureParser(this));
        parsers.add(new MacroParser(this));
        parsers.add(new CommentParser());
        parsers.add(new NumberParser());
        parsers.add(new ForLoopParser(this));
        parsers.add(new IfStatementParser(this));
        parsers.add(new BuiltinParser());
        parsers.add(new ExternalFunctionParser(this));
        Builtins.build(this, stdl);
    }

    public InlineFunction parse(String id, String s) {
        InlineFunction inlineFunction = new InlineFunction(id);
        parse(new StringIterator(s), inlineFunction);
        return inlineFunction;
    }

    public NylonFunction parse(StringIterator s) {
        InlineFunction inlineFunction = new InlineFunction("LambdaFunction");
        parse(inlineFunction, s);
        if (inlineFunction.functions.size() != 0) {
            return inlineFunction.functions.get(0);
        } else {
            return inlineFunction;
        }
    }

    public InlineFunction parseUntil(StringIterator in, Predicate<StringIterator> test) {
        InlineFunction inlineFunction = new InlineFunction("LambdaFunction");
        parseUntil(in, inlineFunction, test);
        return inlineFunction;
    }

    @Override
    public boolean parse(InlineFunction inlineFunction, StringIterator in) throws ParseException {
        in.skipWhitespace();
        return super.parse(inlineFunction, in);
    }
}
