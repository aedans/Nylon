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
    override fun accept(parsers: HashMap<Char, Parser>) {
        parsers['_'] = ArgumentParser()
    }
}

class ArgumentParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        src.next()
        return object : NylonFunction("_", 1) {
            override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) = args[0].apply(stack)

            override fun toString(args: ArrayList<NylonFunction>): String {
                if (args.size == 0)
                    return "_"
                else
                    return args[0].toString()
            }
        }
    }
}