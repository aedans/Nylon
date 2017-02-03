package nylon.builtins

import nylon.NylonFunction
import nylon.NylonFunctionPrototype
import nylon.NylonStack
import nylon.nylonobjects.FunctionObject
import nylon.parser.parsers.BuiltinTemplateParserBuilder

/**
 * Created by Aedan Smith.
 */

class CaptureBuiltinBuilder : BuiltinTemplateParserBuilder(object : NylonFunctionPrototype("Capture") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.push(FunctionObject(args[0]))
    }
}, 1, '@')
