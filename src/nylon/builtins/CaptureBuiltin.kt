package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.FunctionObject
import nylon.parser.parsers.BuiltinParserBuilder

/**
 * Created by Aedan Smith.
 */

class CaptureBuiltinBuilder : BuiltinParserBuilder(object : NylonFunction("Capture", 1) {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.push(FunctionObject(args[0]))
    }
}, '@')
