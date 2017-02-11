package nylon.nylonobjects

import nylon.NylonFunction
import nylon.NylonObject
import nylon.concatenate

/**
 * Created by Aedan Smith.
 */

class FunctionObject(value: NylonFunction) : NylonObject<NylonFunction>(value, "$value") {
    override fun toDouble() = value.apply().peek().toDouble()

    override fun toFunction() = value

    override fun iterator() = value.apply().iterator()

    override fun toList() = value.apply().toList()

    override fun add(nylonObject: NylonObject<*>) = FunctionObject(concatenate(value, nylonObject.toFunction()))
    override fun subt(nylonObject: NylonObject<*>) = throw RuntimeException("Could not subtract function.")
    override fun mult(nylonObject: NylonObject<*>) = FunctionObject(concatenate((0..nylonObject.toInt() - 1).map { value }))
    override fun divd(nylonObject: NylonObject<*>) = throw RuntimeException("Could not divide function.")

    override fun clone() = FunctionObject(value)
}
