package nylon.builtins

import nylon.NylonFunction
import nylon.NylonFunctionPrototype
import nylon.NylonStack
import nylon.parser.parsers.BuiltinTemplateParserBuilder

/**
 * Created by Aedan Smith.
 */

// Consuming, pushing
class ForîLoopBuiltinParserBuilder : BuiltinTemplateParserBuilder(object : NylonFunctionPrototype("Forî") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.pop().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 1, 'î')

// Non-consuming, pushing
class ForìLoopBuiltinParserBuilder : BuiltinTemplateParserBuilder(object : NylonFunctionPrototype("Forì") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.peek().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 1, 'ì')

// Consuming, non-pushing
class ForíLoopBuiltinParserBuilder : BuiltinTemplateParserBuilder(object : NylonFunctionPrototype("Forí") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.pop().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 1, 'í')

// Non-consuming, non-pushing
class ForïLoopBuiltinParserBuilder : BuiltinTemplateParserBuilder(object : NylonFunctionPrototype("Forï") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.peek().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 1, 'ï')
