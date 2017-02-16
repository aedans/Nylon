package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class CharObject(value: Char) : NylonObject<Char>(value, "Char($value)") {
    override fun toDouble() = value.toDouble()

    override fun iterator() = (0..value.toInt() - 1).map { CharObject(it.toChar()) }.iterator()

    override fun add(nylonObject: NylonObject<*>) = CharObject(value + nylonObject.toChar().toInt())
    override fun subt(nylonObject: NylonObject<*>) = CharObject(value - nylonObject.toInt())
    override fun mult(nylonObject: NylonObject<*>) = CharObject((value.toInt() * nylonObject.toInt()).toChar())
    override fun divd(nylonObject: NylonObject<*>) = CharObject((value.toInt() / nylonObject.toInt()).toChar())

    override fun clone() = CharObject(value)
}
