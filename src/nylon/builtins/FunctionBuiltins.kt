package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CallFunctionParserBuilder : BuiltinParserBuilder(object : NylonFunction(",") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().toFunction().apply(stack)
    }
}, ',')
