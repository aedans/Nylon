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

class FunctionCallParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        for (c in 'a'..'z') {
            parsers[c.toInt()] = FunctionCallParser()
        }
        for (c in 'A'..'Z') {
            parsers[c.toInt()] = FunctionCallParser()
        }
    }
}

class FunctionCallParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        val name = src.parseNextName()
        try {
            val function = parser.functions[name]!!.get()
            function.args = Array(function.argNum) { parser.parse(src)!! }
            return function
        } catch (e: NullPointerException) {
            throw RuntimeException("Could not find function with name \"$name\"")
        }
    }
}

fun CharIterator.parseNextName(): String {
    var name = ""
    while (this.hasNext()) {
        if (peek() in 'A'..'Z' || peek() == '_' || peek() == '-') {
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

