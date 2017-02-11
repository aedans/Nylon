package nylon.nylonobjects

import nylon.NylonObject

/**
 * Created by Aedan Smith.
 */

class StringObject(value: List<NylonObject<*>>) : ListObject<Char>(toCharObjectList(toString(value))) {
    constructor(s: String) : this(toCharObjectList(s))

    override fun toDouble(): Double = toString(value).toDouble()

    override fun toLong() = toString(value).toLong()

    override fun clone() = StringObject(value)

    override fun toString() = "String(\"${toString(value)}\")"

    override fun add(nylonObject: NylonObject<*>) = StringObject(toString(super.add(nylonObject).toList()))

    override fun subt(nylonObject: NylonObject<*>) = StringObject(toString(super.subt(nylonObject).toList()))

    override fun mult(nylonObject: NylonObject<*>) = StringObject(toString(super.mult(nylonObject).toList()))

    override fun outputString() = toString(value)
}

fun toCharObjectList(s: String): List<NylonObject<Char>> {
    return s.map(::CharObject)
}

fun toString(s: List<NylonObject<*>>): String {
    var string = ""
    s.forEach { string += it.value }
    return string
}
