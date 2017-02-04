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

    fun getStack() = try {
        stacks.peek()!!
    } catch (e: Exception) {
        throw NylonException("No active stack found")
    }

    fun peek(i: Int = 1) = try {
        getStack()[getStack().size - i]!!
    } catch (e: Exception) {
        throw NylonException("Could not find object at depth $i in stack ${getStack()}")
    }

    fun push(o: NylonObject<*>) = try {
        getStack().push(o)!!
    } catch (e: Exception) {
        throw NylonException("Could not push object $o to stack ${getStack()}")
    }

    fun pop() = try {
        getStack().pop()!!
    } catch (e: Exception) {
        throw NylonException("Could not pop object from stack ${getStack()}")
    }

    fun add(o: NylonObject<*>) = try {
        getStack().add(o)
    } catch (e: Exception) {
        throw NylonException("Could not add object $o to stack ${getStack()}")
    }

    fun add(i: Int, o: NylonObject<*>) = try {
        getStack().add(i, o)
    } catch (e: Exception) {
        throw NylonException("Could not add object $o to stack ${getStack()} at index $i")
    }

    fun removeAt(i: Int) = try {
        getStack().removeAt(i)!!
    } catch (e: Exception) {
        throw NylonException("Could not remove object at index $i from stack ${getStack()}")
    }

    fun getSize() = getStack().size

    fun iterator() = getStack().iterator()

    fun toList() = getStack().toList()

    fun clear() = getStack().clear()

    override fun toString() = getStack().toString()
}
