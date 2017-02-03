package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.DoubleObject
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class AdditionParserBuilder : BuiltinParserBuilder(object : NylonFunction("+") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        stack.add(o1 + o2)
    }
}, '+')

class SubtractionParserBuilder : BuiltinParserBuilder(object : NylonFunction("-") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        stack.add(o1 - o2)
    }
}, '-')

class MultiplicationParserBuilder : BuiltinParserBuilder(object : NylonFunction("*") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        stack.add(o1 * o2)
    }
}, '*')

class DivisionParserBuilder : BuiltinParserBuilder(object : NylonFunction("/") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        stack.add(o1 / o2)
    }
}, '/')

class ModParserBuilder : BuiltinParserBuilder(object : NylonFunction("%") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        // TODO
        throw RuntimeException("Mod operator (%) is not currently implemented.")
    }
}, '%')

class ExponentParserBuilder : BuiltinParserBuilder(object : NylonFunction("^") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val o2 = stack.pop()
        val o1 = stack.pop()
        stack.add(DoubleObject(Math.pow(o1.toDouble(), o2.toDouble())))
    }
}, '^')
