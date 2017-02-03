package nylon.parser.parsers

import nylon.NylonFunction
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class FunctionParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers['{'.toInt()] = FunctionParser()
    }
}

class FunctionParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()

        return parser.parseUntil(src, '}')
    }
}
