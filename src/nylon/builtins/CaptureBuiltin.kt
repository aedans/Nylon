package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.FunctionObject
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CaptureBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("@", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(FunctionObject(args[0]))
    }
}, '@')
