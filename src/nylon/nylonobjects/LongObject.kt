package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class LongObject(value: Long) : NylonObject<Long>(value, "Long($value)") {
    override fun toDouble() = value.toDouble()

    override fun iterator() = (0..value - 1).map(::LongObject).iterator()

    override fun add(nylonObject: NylonObject<*>) = LongObject(value + nylonObject.toLong())
    override fun subt(nylonObject: NylonObject<*>) = LongObject(value - nylonObject.toLong())
    override fun mult(nylonObject: NylonObject<*>) = LongObject(value * nylonObject.toLong())
    override fun divd(nylonObject: NylonObject<*>) = LongObject(value / nylonObject.toLong())

    override fun clone() = LongObject(value)
}
