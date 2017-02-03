package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

// Consuming, pushing
class ForîLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("î", 1) {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 'î')

// Non-consuming, pushing
class ForìLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("ì", 1) {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.peek().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 'ì')

// Consuming, non-pushing
class ForíLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("í", 1) {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 'í')

// Non-consuming, non-pushing
class ForïLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("ï", 1) {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.peek().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 'ï')
