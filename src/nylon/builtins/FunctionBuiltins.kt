package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder

/**
 * Created by Aedan Smith.
 */

class CallFunctionParserBuilder : BuiltinParserBuilder(object : NylonFunction("Call") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.pop().toFunction().apply(stack)
    }
}, ',')
