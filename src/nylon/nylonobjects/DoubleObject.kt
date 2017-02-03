package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class DoubleObject(value: Double) : NylonObject<Double>(value, "Double($value)") {
    override fun toDouble(): Double = value

    override fun iterator(): Iterator<NylonObject<*>> {
        return object : Iterator<DoubleObject> {
            var d = 0.toDouble()

            override fun hasNext(): Boolean = d < value

            override fun next(): DoubleObject = DoubleObject(d++)
        }
    }

    override fun add(nylonObject: NylonObject<*>): NylonObject<*> {
        return DoubleObject(value + nylonObject.toDouble())
    }

    override fun subt(nylonObject: NylonObject<*>): NylonObject<*> {
        return DoubleObject(value - nylonObject.toDouble())
    }

    override fun mult(nylonObject: NylonObject<*>): NylonObject<*> {
        return DoubleObject(value * nylonObject.toDouble())
    }

    override fun divd(nylonObject: NylonObject<*>): NylonObject<*> {
        return DoubleObject(value / nylonObject.toDouble())
    }

    override fun clone(): DoubleObject {
        return DoubleObject(value)
    }
}
