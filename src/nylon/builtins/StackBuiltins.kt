package nylon.builtins

import nylon.NylonFunction
import nylon.NylonObject
import nylon.NylonStack
import nylon.nylonobjects.LongObject
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class PushStackParserBuilder : BuiltinParserBuilder(object : NylonFunction("(") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.stacks.push(Stack<NylonObject<*>>())
    }
}, '(')

class PopStackParserBuilder : BuiltinParserBuilder(object : NylonFunction(")") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.stacks.pop()
    }
}, ')')

class PullTopParserBuilder : BuiltinParserBuilder(object : NylonFunction("À") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.add(stack.stacks[stack.stacks.size - 2].pop())
    }
}, 'À')

class PushTopParserBuilder : BuiltinParserBuilder(object : NylonFunction("Á") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.stacks[stack.stacks.size - 2].add(stack.pop())
    }
}, 'Á')

class CloneParserBuilder : BuiltinParserBuilder(object : NylonFunction(":") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.push(stack.peek().clone())
    }
}, ':')

class MoveToBottomParserBuilder : BuiltinParserBuilder(object : NylonFunction("ù") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.add(0, stack.pop())
    }
}, 'ù')

class MoveToTopParserBuilder : BuiltinParserBuilder(object : NylonFunction("ú") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.push(stack.removeAt(0))
    }
}, 'ú')

class LengthOfStackParserBuilder : BuiltinParserBuilder(object : NylonFunction("Ï") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
        stack.push(LongObject(stack.getSize().toLong()))
    }
}, 'Ï')
