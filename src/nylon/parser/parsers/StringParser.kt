package nylon.parser.parsers

import nylon.NylonFunction
import nylon.createProvider
import nylon.nylonobjects.StringObject
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class StringParserBuilder : ParserBuilder {
    override fun accept(parsers: HashMap<Char, Parser>) {
        parsers['"'] = StringParser()
    }
}

class StringParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        var string = ""
        src.next()
        while (src.hasNext() && src.peek() != '"') {
            when (src.peek()) {
                '\\' -> {
                    src.next()
                    string += src.next()
                }
                else -> {
                    string += src.next()
                }
            }
        }
        if (src.hasNext()) {
            src.next()
        }
        return createProvider(StringObject(string), "\"${string.replace("\"", "\\\"")}\"")
    }
}
