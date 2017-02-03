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

class DefaultParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        for (i: Int in 0..255) {
            parsers.add(DefaultParser())
        }
    }
}

class DefaultParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        return null
    }
}
