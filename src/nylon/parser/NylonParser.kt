package nylon.parser

import nylon.NylonFunction
import nylon.buildBuiltins
import nylon.concatenate
import nylon.parser.parsers.*
import java.io.Reader
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

    fun parseAll(src: String) = parseAll(CharIterator(src))

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
            if (hasNext(1) && peek(1) == '/') {
                while (hasNext() && next() != '\n') {
                }
                continue
            } else return
        }
        next()
    }
}

fun Reader.readExtendedText(): String {
    var s = ""
    while (ready()) {
        val c = read().toChar()
        if (c == '\\') {
            val n = read().toChar().toString() + read().toChar().toString()
            s += when (n) {
                "al" -> 'À'
                "ar" -> 'Á'
                "cc" -> 'â'
                "cd" -> 'à'
                "cf" -> 'å'
                "cl" -> 'ä'
                "cn" -> 'á'
                "cs" -> 'ã'
                "fn" -> 'ƒ'
                "ld" -> 'ï'
                "lp" -> 'ì'
                "lh" -> 'î'
                "lc" -> 'í'
                "ps" -> 'ð'
                "mt" -> 'ù'
                "mb" -> 'ú'
                "ss" -> 'Ï'
                "uq" -> '¿'
                else -> "\\" + n
            }
        } else {
            s += c
        }
    }
    return s
}
