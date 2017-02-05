package nylon.nylonobjects

import nylon.NylonObject
import java.util.*

/**
 * Created by Aedan Smith.
 */

class StringObject(value: List<NylonObject<*>>) : ListObject<Char>(toCharObjectList(toString(value))) {
    constructor(s: String) : this(toCharObjectList(s))

    override fun toDouble(): Double = toString(value).toDouble()

    override fun toLong(): Long = toString(value).toLong()

    override fun clone(): StringObject {
        return StringObject(value)
    }

    override fun toString(): String {
        return "String(\"${toString(value)}\")"
    }

    override fun add(nylonObject: NylonObject<*>): ListObject<*> {
        return StringObject(toString(super.add(nylonObject).toList()))
    }

    override fun subt(nylonObject: NylonObject<*>): ListObject<*> {
        return StringObject(toString(super.subt(nylonObject).toList()))
    }

    override fun mult(nylonObject: NylonObject<*>): ListObject<Char> {
        return StringObject(toString(super.mult(nylonObject).toList()))
    }

    override fun outputString(): String {
        return toString(value)
    }
}

fun toCharObjectList(s: String): List<NylonObject<Char>> {
    val list = ArrayList<NylonObject<Char>>(s.length)
    s.forEach { list.add(CharObject(it)) }
    return list
}

fun toString(s: List<NylonObject<*>>): String {
    var string = ""
    s.forEach { string += it.value }
    return string
}
