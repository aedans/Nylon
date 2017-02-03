package nylon.parser

import nylon.NylonFunction
import nylon.buildBuiltins
import nylon.concatenate
import nylon.parser.parsers.*
import java.util.*
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * Created by Aedan Smith.
 */

interface Parser : BiFunction<CharIterator, NylonParser, NylonFunction?>

interface ParserBuilder : Consumer<HashMap<Char, Parser>>

class NylonParser(vararg builders: ParserBuilder) {
    val parsers = HashMap<Char, Parser>(256)

    val functions = HashMap<String, Supplier<NylonFunction>>()

    constructor() : this(
            DefaultParserBuilder(),
            NumberParserBuilder(),
            StringParserBuilder(),
            CharacterParserBuilder(),
            FunctionParserBuilder(),
            IfStatementParserBuilder(),
            FunctionCallParserBuilder(),
            ArgumentParserBuilder(),
            MacroParserBuilder()
    ) {
        buildBuiltins(this)
    }

    init {
        builders.forEach { it.accept(parsers) }
    }

    fun parseAll(src: String): NylonFunction = parseAll(CharIterator(src))

    fun parseAll(src: CharIterator): NylonFunction {
        val functions = ArrayList<NylonFunction>()
        while (true) {
            val nylonFunction = parse(src)
            if (nylonFunction != null) {
                functions.add(nylonFunction)
            } else {
                break
            }
        }
        return concatenate(functions)
    }

    fun parse(src: CharIterator): NylonFunction? {
        while (true) {
            src.skipUntilStatement()
            if (!src.hasNext())
                break
            val nylonFunction = parsers[src.peek()]!!.apply(src, this)
            if (nylonFunction != null) {
                return nylonFunction
            }
        }
        return null
    }

    fun parseUntil(src: CharIterator, c: Char): NylonFunction? {
        val nylonFunctions = ArrayList<NylonFunction>()
        while (src.hasNext()) {
            if (!src.hasNext() || src.peek() == c)
                break
            val nylonFunction = parsers[src.peek()]!!.apply(src, this)
            if (nylonFunction != null) {
                nylonFunctions.add(nylonFunction)
            }
        }
        if (src.hasNext())
            src.next()
        return concatenate(nylonFunctions)
    }
}

fun CharIterator.skipUntilStatement() {
    while (hasNext() && (peek() <= ' ' || peek() == '/')) {
        if (peek() == '/') {
            if (peek(1) == '/') {
                while (hasNext() && next() != '\n') {
                }
                continue
            } else return
        }
        next()
    }
}
