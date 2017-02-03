package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class LongObject(value: Long) : NylonObject<Long>(value, "Long($value)") {
    override fun toDouble(): Double = value.toDouble()

    override fun iterator(): Iterator<NylonObject<*>> {
        return object : Iterator<LongObject> {
            var l = 0.toLong()

            override fun hasNext(): Boolean = l < value

            override fun next(): LongObject = LongObject(l++)
        }
    }

    override fun add(nylonObject: NylonObject<*>): NylonObject<*> {
        return LongObject(value + nylonObject.toLong())
    }

    override fun subt(nylonObject: NylonObject<*>): NylonObject<*> {
        return LongObject(value - nylonObject.toLong())
    }

    override fun mult(nylonObject: NylonObject<*>): NylonObject<*> {
        return LongObject(value * nylonObject.toLong())
    }

    override fun divd(nylonObject: NylonObject<*>): NylonObject<*> {
        return LongObject(value / nylonObject.toLong())
    }

    override fun clone(): LongObject {
        return LongObject(value)
    }
}
