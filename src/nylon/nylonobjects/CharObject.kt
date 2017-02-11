package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class CharObject(value: Char) : NylonObject<Char>(value, "Char($value)") {
    override fun toDouble() = value.toDouble()

    override fun iterator() = object : Iterator<CharObject> {
        var c = 0

        override fun hasNext() = c < value.toInt()

        override fun next() = CharObject(c++.toChar())
    }

    override fun add(nylonObject: NylonObject<*>) = CharObject(value + nylonObject.toChar().toInt())
    override fun subt(nylonObject: NylonObject<*>) = CharObject(value - nylonObject.toInt())
    override fun mult(nylonObject: NylonObject<*>) = CharObject((value.toInt() * nylonObject.toInt()).toChar())
    override fun divd(nylonObject: NylonObject<*>) = CharObject((value.toInt() / nylonObject.toInt()).toChar())

    override fun clone() = CharObject(value)
}
