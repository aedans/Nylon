package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.FunctionObject
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CaptureBuiltinBuilder : BuiltinParserBuilder(object : NylonFunction("Capture", 1) {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(FunctionObject(args[0]))
    }
}, '@')
