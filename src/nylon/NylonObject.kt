package nylon

/**
 * Created by Aedan Smith.
 */

@Suppress("EqualsOrHashCode")
abstract class NylonObject<out T>(val value: T, val string: String) : Iterable<NylonObject<*>>, Comparable<NylonObject<*>> {
    abstract fun toDouble(): Double

    fun toFloat() = toDouble().toFloat()

    open fun toLong() = toDouble().toLong()

    fun toInt() = toLong().toInt()

    fun toShort() = toLong().toShort()

    fun toByte() = toLong().toByte()

    open fun toChar() = toDouble().toChar()

    open fun toBoolean() = toDouble().toInt() != 0

    open fun toList(): List<NylonObject<*>> = listOf(this)

    open fun toFunction() = createProvider(this, "@$this")

    operator fun plus(other: NylonObject<*>) = add(other)
    abstract fun add(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun minus(other: NylonObject<*>) = subt(other)
    abstract fun subt(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun times(other: NylonObject<*>) = mult(other)
    abstract fun mult(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun div(other: NylonObject<*>) = divd(other)
    abstract fun divd(nylonObject: NylonObject<*>): NylonObject<*>

    abstract fun clone(): NylonObject<*>

    override fun equals(other: Any?) = if (other is NylonObject<*>) this.value == other.value else false

    override operator fun compareTo(other: NylonObject<*>) = java.lang.Double.compare(toDouble(), other.toDouble())

    override fun toString() = string

    open fun outputString() = value.toString()
}
