package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class CharObject(value: Char) : NylonObject<Char>(value, "Char($value)") {
    override fun toDouble(): Double = value.toDouble()

    override fun iterator(): Iterator<NylonObject<*>> {
        return object : Iterator<CharObject> {
            var c = 0

            override fun hasNext(): Boolean = c < value.toInt()

            override fun next(): CharObject = CharObject(c++.toChar())
        }
    }

    override fun add(nylonObject: NylonObject<*>): NylonObject<*> {
        return CharObject(value + nylonObject.toChar().toInt())
    }

    override fun subt(nylonObject: NylonObject<*>): NylonObject<*> {
        return CharObject(value - nylonObject.toInt())
    }

    override fun mult(nylonObject: NylonObject<*>): NylonObject<*> {
        return CharObject((value.toInt() * nylonObject.toInt()).toChar())
    }

    override fun divd(nylonObject: NylonObject<*>): NylonObject<*> {
        return CharObject((value.toInt() / nylonObject.toInt()).toChar())
    }

    override fun clone(): CharObject {
        return CharObject(value)
    }
}
