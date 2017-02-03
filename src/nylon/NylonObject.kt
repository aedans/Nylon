package nylon

/**
 * Created by Aedan Smith.
 */

@Suppress("EqualsOrHashCode")
abstract class NylonObject<out T>(val value: T, val string: String) : Iterable<NylonObject<*>>, Comparable<NylonObject<*>> {
    abstract fun toDouble(): Double

    fun toFloat() = toDouble().toFloat()

    open fun toLong(): Long = toDouble().toLong()

    fun toInt() = toLong().toInt()

    fun toShort() = toLong().toShort()

    fun toByte() = toLong().toByte()

    open fun toChar(): Char = toDouble().toChar()

    open fun toBoolean(): Boolean = toDouble().toInt() != 0

    open fun toList(): List<NylonObject<*>> = listOf(this)

    open fun toFunction(): NylonFunction = createProvider(this)

    operator fun plus(other: NylonObject<*>): NylonObject<*> = add(other)
    abstract fun add(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun minus(other: NylonObject<*>): NylonObject<*> = subt(other)
    abstract fun subt(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun times(other: NylonObject<*>): NylonObject<*> = mult(other)
    abstract fun mult(nylonObject: NylonObject<*>): NylonObject<*>

    operator fun div(other: NylonObject<*>): NylonObject<*> = divd(other)
    abstract fun divd(nylonObject: NylonObject<*>): NylonObject<*>

    abstract fun clone(): NylonObject<*>

    override fun equals(other: Any?): Boolean = if (other is NylonObject<*>) this.value == other.value else false

    override operator fun compareTo(other: NylonObject<*>): Int = java.lang.Double.compare(toDouble(), other.toDouble())

    override fun toString(): String = string

    open fun outputString(): String = value.toString()
}
