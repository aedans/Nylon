package nylon.parser.parsers

import nylon.NylonFunction
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*
import java.util.function.Supplier

/**
 * Created by Aedan Smith.
 */

class MacroParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers['#'.toInt()] = MacroParser()
    }
}

class MacroParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        val name = src.parseNextName()
        val function = parser.parse(src)
        parser.functions.put(name, Supplier { function!!.clone() })
        return null
    }
}


