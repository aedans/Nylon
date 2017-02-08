package nylon.parser

/**
 * Created by Aedan Smith.
 */

class CharIterator(val chars: String) : Iterator<Char> {
    var index = 0

    override fun next(): Char {
        if (index >= chars.length)
            throw RuntimeException("Unexpected end of string.")
        return chars[index++]
    }

    fun peek(i: Int = 0): Char {
        if (index + i >= chars.length)
            throw RuntimeException("Unexpected end of string.")
        return chars[index + i]
    }

    override fun hasNext() = index < chars.length

    fun hasNext(i: Int) = index + i < chars.length
}
