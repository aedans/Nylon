package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.*
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CastToDoubleParserBuilder : BuiltinParserBuilder(object : NylonFunction("à") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(DoubleObject(stack.pop().toDouble()))
    }
}, 'à')

class CastToLongParserBuilder : BuiltinParserBuilder(object : NylonFunction("á") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(LongObject(stack.pop().toLong()))
    }
}, 'á')

class CastToCharParserBuilder : BuiltinParserBuilder(object : NylonFunction("â") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(CharObject(stack.pop().toChar()))
    }
}, 'â')

class CastToListParserBuilder : BuiltinParserBuilder(object : NylonFunction("ä") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(ListObject(stack.pop().toList()))
    }
}, 'ä')

class CastToStringParserBuilder : BuiltinParserBuilder(object : NylonFunction("ã") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(StringObject(stack.pop().toList()))
    }
}, 'ã')

class CastToFunctionParserBuilder : BuiltinParserBuilder(object : NylonFunction("å") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(FunctionObject(stack.pop().toFunction()))
    }
}, 'å')
