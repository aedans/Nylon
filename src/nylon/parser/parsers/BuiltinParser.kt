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

open class BuiltinParserBuilder(val nylonFunction: NylonFunction, val identifier: Char) : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers[identifier.toInt()] = BuiltinParser(nylonFunction)
    }
}

class BuiltinParser(val nylonFunction: NylonFunction) : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        val function = nylonFunction.clone()
        function.resolveArgs(Supplier { parser.parse(src)!! })
        return function
    }
}
