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

class FunctionCallParserBuilder : ParserBuilder {
    override fun accept(parsers: HashMap<Char, Parser>) {
        for (c in ('a'..'z') + ('A'..'Z')) {
            parsers[c] = FunctionCallParser()
        }
    }
}

class FunctionCallParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        val name = src.parseNextName()
        if (!parser.functions.contains(name))
            throw RuntimeException("Could not find function with name \"$name\"")
        val function = parser.functions[name]!!.get()
        function.resolveNestedArgs(Supplier { parser.parse(src)!! })
        return function
    }
}

fun CharIterator.parseNextName(): String {
    var name = ""
    while (this.hasNext()) {
        if (peek() in ('A'..'Z') + '_' + '-') {
            name += next()
        } else if (peek() in 'a'..'z') {
            name += next()
            break
        } else {
            break
        }
    }
    return name
}

