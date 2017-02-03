package nylon.nylonobjects

import nylon.NylonFunction
import nylon.NylonObject
import nylon.concatenate
import java.util.*

/**
 * Created by Aedan Smith.
 */

class FunctionObject(value: NylonFunction) : NylonObject<NylonFunction>(value, "$value") {
    override fun toDouble(): Double = value.apply().peek().toDouble()

    override fun toFunction(): NylonFunction = value

    override fun iterator(): Iterator<NylonObject<*>> = value.apply().iterator()

    override fun toList(): List<NylonObject<*>> = value.apply().toList()

    override fun add(nylonObject: NylonObject<*>): FunctionObject {
        return FunctionObject(concatenate(value, nylonObject.toFunction()))
    }

    override fun subt(nylonObject: NylonObject<*>): NylonObject<*> {
        throw RuntimeException("Could not subtract function.")
    }

    override fun mult(nylonObject: NylonObject<*>): FunctionObject {
        val n = nylonObject.toInt()
        val functions = ArrayList<NylonFunction>(n)
        for (i in 0..n - 1) {
            functions.add(value)
        }
        return FunctionObject(concatenate(functions))
    }

    override fun divd(nylonObject: NylonObject<*>): NylonObject<*> {
        throw RuntimeException("Could not divide function.")
    }

    override fun clone(): FunctionObject {
        return FunctionObject(value)
    }
}
