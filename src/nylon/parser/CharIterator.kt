package nylon.parser

/**
 * Created by Aedan Smith.
 */

class CharIterator(val chars: String) : Iterator<Char> {
    var index = 0

    override fun next(): Char {
        return chars[index++]
    }

    override fun hasNext(): Boolean {
        return index < chars.length
    }

    fun peek(i: Int = 0): Char {
        return chars[index + i]
    }
}
