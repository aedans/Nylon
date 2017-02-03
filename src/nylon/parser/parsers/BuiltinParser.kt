package nylon.parser.parsers

import nylon.NylonFunction
import nylon.NylonFunctionPrototype
import nylon.applyTemplates
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

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
        return nylonFunction
    }
}

open class BuiltinTemplateParserBuilder(val prototype: NylonFunctionPrototype, val numArgs: Int, val identifier: Char) : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers[identifier.toInt()] = BuiltinTemplateParser(prototype, numArgs)
    }
}

class BuiltinTemplateParser(val prototype: NylonFunctionPrototype, val numArgs: Int) : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()

        val args = Array(numArgs, {
            return@Array parser.parse(src)!!
        })

        return applyTemplates(prototype, args)
    }
}
