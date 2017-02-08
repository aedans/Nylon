package nylon.nylonobjects

import nylon.NylonObject
import java.util.*

/**
 * Created by Aedan Smith.
 */

open class ListObject<out T>(value: List<NylonObject<T>>) : NylonObject<List<NylonObject<T>>>(value, "List($value)") {
    override fun toDouble() = value.size.toDouble()

    override fun toList() = value

    override fun iterator() = value.iterator()

    override fun add(nylonObject: NylonObject<*>): ListObject<*> {
        val objectList = nylonObject.toList()
        val newList = ArrayList<NylonObject<*>>(value.size + objectList.size)
        newList.addAll(value)
        newList.addAll(objectList)
        return ListObject(newList)
    }

    override fun subt(nylonObject: NylonObject<*>): ListObject<*> {
        val objectList = nylonObject.toList()
        val newList = ArrayList<NylonObject<*>>(value.size)
        newList.addAll(value)
        newList.removeAll(objectList)
        return ListObject(newList)
    }

    override fun mult(nylonObject: NylonObject<*>): ListObject<T> {
        val n = nylonObject.toInt()
        val pn = Math.abs(n)
        val objectList = ArrayList<NylonObject<T>>(pn)
        for (i in 0..pn - 1) {
            if (n > 0) {
                objectList += value
            } else {
                objectList += value.reversed()
            }
        }
        return ListObject(objectList)
    }

    override fun divd(nylonObject: NylonObject<*>) = throw RuntimeException("Cannot divide list.")

    override fun clone(): ListObject<*> {
        val newValue = ArrayList<NylonObject<*>>(value.size)
        value.forEach { newValue.add(it.clone()) }
        return ListObject(newValue)
    }

    // Copied from AbstractCollection.toString()
    override fun outputString(): String {
        val it = iterator()
        if (!it.hasNext())
            return "[]"

        val sb = StringBuilder()
        sb.append('[')
        while (true) {
            val e = it.next()
            sb.append(if (e === this) "(this Collection)" else e.outputString())
            if (!it.hasNext())
                return sb.append(']').toString()
            sb.append(',').append(' ')
        }
    }
}
