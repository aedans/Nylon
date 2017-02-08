package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class LongObject(value: Long) : NylonObject<Long>(value, "Long($value)") {
    override fun toDouble() = value.toDouble()

    override fun iterator() = object : Iterator<LongObject> {
        var l = 0.toLong()

        override fun hasNext() = l < value

        override fun next() = LongObject(l++)
    }

    override fun add(nylonObject: NylonObject<*>) = LongObject(value + nylonObject.toLong())

    override fun subt(nylonObject: NylonObject<*>) = LongObject(value - nylonObject.toLong())

    override fun mult(nylonObject: NylonObject<*>) = LongObject(value * nylonObject.toLong())

    override fun divd(nylonObject: NylonObject<*>) = LongObject(value / nylonObject.toLong())

    override fun clone() = LongObject(value)
}
