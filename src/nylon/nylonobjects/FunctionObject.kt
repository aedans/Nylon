package nylon.nylonobjects

import nylon.NylonFunction
import nylon.NylonObject
import nylon.concatenate
import java.util.*

/**
 * Created by Aedan Smith.
 */

class FunctionObject(value: NylonFunction) : NylonObject<NylonFunction>(value, "$value") {
    override fun toDouble() = value.apply().peek().toDouble()

    override fun toFunction() = value

    override fun iterator() = value.apply().iterator()

    override fun toList() = value.apply().toList()

    override fun add(nylonObject: NylonObject<*>): FunctionObject {
        val functions = ArrayList<NylonFunction>(2)
        functions.add(value)
        functions.add(nylonObject.toFunction())
        return FunctionObject(concatenate(functions))
    }

    override fun subt(nylonObject: NylonObject<*>) = throw RuntimeException("Could not subtract function.")

    override fun mult(nylonObject: NylonObject<*>): FunctionObject {
        val n = nylonObject.toInt()
        val functions = ArrayList<NylonFunction>(n)
        for (i in 0..n - 1) {
            functions.add(value)
        }
        return FunctionObject(concatenate(functions))
    }

    override fun divd(nylonObject: NylonObject<*>) = throw RuntimeException("Could not divide function.")

    override fun clone() = FunctionObject(value)
}
