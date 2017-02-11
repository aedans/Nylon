package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.FunctionObject
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import nylon.parser.parsers.BuiltinParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CaptureBuiltinParserBuilder : BuiltinParserBuilder(object : NylonFunction("@", 1) {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.push(FunctionObject(args[0]))
    }
}, '@')

class CallFunctionParserBuilder : BuiltinParserBuilder(object : NylonFunction(",") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        stack.pop().toFunction().apply(stack)
    }
}, ',')

class PrintParserBuilder : BuiltinParserBuilder(object : NylonFunction("`") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        print(stack.pop().outputString())
    }
}, '`')

class AsciiCanvasParserBuilder : BuiltinParserBuilder(object : NylonFunction("ð") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        throw RuntimeException("Ascii canvas (ð) is currently not implemented.")
    }
}, 'ð')

class ParserParserBuilder : ParserBuilder {
    override fun accept(parsers: HashMap<Char, Parser>) {
        parsers.put('ƒ', object : Parser {
            override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
                src.next()
                return object : NylonFunction("ƒ") {
                    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
                        stack.push(FunctionObject(parser.parseAll(stack.pop().outputString())))
                    }
                }
            }
        })
    }
}
