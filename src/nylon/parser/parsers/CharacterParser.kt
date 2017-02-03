package nylon.parser.parsers

import nylon.NylonFunction
import nylon.createProvider
import nylon.nylonobjects.CharObject
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CharacterParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers['\''.toInt()] = CharacterParser()
    }
}

class CharacterParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        return createProvider(CharObject(src.next()))
    }
}
