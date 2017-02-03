package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder

/**
 * Created by Aedan Smith.
 */

class AsciiCanvasParserBuilder : BuiltinParserBuilder(object : NylonFunction("ð") {
    override fun apply(stack: NylonStack) {
        throw RuntimeException("Ascii canvas (ð) is currently not implemented.")
    }
}, 'ð')
