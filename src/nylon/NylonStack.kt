package nylon

import java.util.*

/**
 * Created by Aedan Smith.
 */

class NylonStack {
    val stacks = Stack<Stack<NylonObject<*>>>()

    init {
        stacks.add(Stack<NylonObject<*>>())
    }

    fun peek(i: Int = 1) = stacks.peek()[stacks.peek().size - i]!!

    fun push(o: NylonObject<*>) = stacks.peek().push(o)!!

    fun pop() = stacks.peek().pop()!!

    fun add(o: NylonObject<*>) = stacks.peek().add(o)

    fun add(i: Int, o: NylonObject<*>) = stacks.peek().add(i, o)

    fun removeAt(i: Int) = stacks.peek().removeAt(i)!!

    fun getSize() = stacks.peek().size

    fun iterator() = stacks.peek().iterator()

    fun toList() = stacks.peek().toList()

    fun clear() = stacks.peek().clear()

    override fun toString() = stacks.peek().toString()
}
