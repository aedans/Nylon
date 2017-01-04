package nylon.parser;

import nylon.InlineFunction;
import nylon.nylonobjects.NylonFunction;
import nylon.parser.parsers.*;
import parser.LinkedParser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class NylonParser extends LinkedParser<StringIterator, InlineFunction> {
    public static NylonParser nylonParser = new NylonParser();

    public NylonParser() {
        super(new DefaultParser(),
                new CharacterParser(),
                new StringParser(),
                new NylonFunctionParser(),
                new CastParser(),
                new CaptureParser(),
                new CommentParser(),
                new NumberParser(),
                new ForLoopParser(),
                new IfStatementParser(),
                new BuiltinParser(),
                new LibraryParser()
        );
    }

    public static InlineFunction parse(String id, String s) {
        InlineFunction inlineFunction = new InlineFunction(id);
        nylonParser.parse(new StringIterator(s), inlineFunction);
        return inlineFunction;
    }

    public static NylonFunction parse(StringIterator s) {
        InlineFunction inlineFunction = new InlineFunction("LambdaFunction");
        nylonParser.parse(inlineFunction, s);
        if (inlineFunction.functions.size() != 0) {
            return inlineFunction.functions.get(0);
        } else {
            return new InlineFunction("LambdaFunction");
        }
    }
}
