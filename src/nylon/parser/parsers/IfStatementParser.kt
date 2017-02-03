package nylon.parser.parsers

import nylon.NylonFunction
import nylon.NylonStack
import nylon.emptyFunction
import nylon.parser.*
import nylon.parser.CharIterator
import java.util.*

/**
 * Created by Aedan Smith.
 */

class IfStatementParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers['?'.toInt()] = IfStatementParser()
        parsers['¿'.toInt()] = IfStatementParser()
        parsers['>'.toInt()] = IfStatementParser()
        parsers['<'.toInt()] = IfStatementParser()
        parsers['='.toInt()] = IfStatementParser()
    }
}

class IfStatementParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        val statement = ArrayList<Char>()
        while (src.peek() == '?' || src.peek() == '¿' || src.peek() == '>' || src.peek() == '<' || src.peek() == '=' || src.peek() == '!') {
            statement.add(src.next())
        }
        val ifTrue = parser.parse(src)
        val ifFalse: NylonFunction

        src.skipUntilStatement()
        if (src.hasNext() && src.peek() == '!') {
            src.next()
            ifFalse = parser.parse(src) as NylonFunction
        } else {
            ifFalse = emptyFunction()
        }

        val pop = statement[statement.size - 1] != '!'

        return object : NylonFunction("If(${String(statement)})<$ifTrue>!<$ifFalse>") {
            override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
                var b = false
                for (it in statement) {
                    when (it) {
                        '?' -> b = stack.peek().toBoolean()
                        '¿' -> b = !stack.peek().toBoolean()
                        '<' -> b = stack.peek(2) < stack.peek()
                        '>' -> b = stack.peek(2) > stack.peek()
                        '=' -> b = stack.peek() == stack.peek(2)
                        '!' -> {
                        }
                        else -> throw RuntimeException("Unrecognized if statement '$it'")
                    }
                    if (b) break
                }
                if (pop)
                    stack.pop()
                if (b) {
                    ifTrue!!.apply(stack)
                } else {
                    ifFalse.apply(stack)
                }
            }
        }
    }

    private fun String(statement: ArrayList<Char>): String {
        var string = ""
        statement.forEach { string += it }
        return string
    }
}
