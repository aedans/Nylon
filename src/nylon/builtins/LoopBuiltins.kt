package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ForîLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("î", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 'î')

class ForìLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("ì", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.peek().iterator().forEach {
            stack.push(it)
            args[0].apply(stack)
        }
    }
}, 'ì')

class ForíLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("í", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 'í')

class ForïLoopBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("ï", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.peek().iterator().forEach {
            args[0].apply(stack)
        }
    }
}, 'ï')

class WhileLoopBuiltinParser : BuiltinParserBuilder(object : NylonFunction("&", 2) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        while (true) {
            args[0].apply(stack)
            if (stack.pop().toBoolean()) {
                args[1].apply(stack)
            } else {
                break
            }
        }
    }
}, '&')
