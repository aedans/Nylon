package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class AsciiCanvasParserBuilder : BuiltinParserBuilder(object : NylonFunction("ð") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        throw RuntimeException("Ascii canvas (ð) is currently not implemented.")
    }
}, 'ð')
