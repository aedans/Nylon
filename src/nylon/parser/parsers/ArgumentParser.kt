package nylon.parser.parsers

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ArgumentParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        parsers['_'.toInt()] = ArgumentParser()
    }
}

class ArgumentParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        return object : NylonFunction("Arg", 1) {
            override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
                args[0].apply(stack)
            }
        }
    }
}