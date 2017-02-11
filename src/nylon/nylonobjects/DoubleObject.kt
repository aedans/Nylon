package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class DoubleObject(value: Double) : NylonObject<Double>(value, "Double($value)") {
    override fun toDouble() = value

    override fun iterator() = object : Iterator<DoubleObject> {
        var d = 0.toDouble()

        override fun hasNext() = d < value

        override fun next() = DoubleObject(d++)
    }

    override fun add(nylonObject: NylonObject<*>) = DoubleObject(value + nylonObject.toDouble())
    override fun subt(nylonObject: NylonObject<*>) = DoubleObject(value - nylonObject.toDouble())
    override fun mult(nylonObject: NylonObject<*>) = DoubleObject(value * nylonObject.toDouble())
    override fun divd(nylonObject: NylonObject<*>) = DoubleObject(value / nylonObject.toDouble())

    override fun clone() = DoubleObject(value)
}
