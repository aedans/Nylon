package nylon

import java.util.function.BiFunction
import java.util.function.Function

/**
 * Created by Aedan Smith.
 */

abstract class NylonFunction(val string: String) : Function<NylonStack, Unit> {
    override fun toString(): String = string

    fun apply(): NylonStack {
        val stack = NylonStack()
        apply(stack)
        return stack
    }
}

abstract class NylonFunctionPrototype(val string: String) : BiFunction<NylonStack, Array<NylonFunction>, Unit> {
    override fun toString(): String = string
}

fun applyTemplates(prototype: NylonFunctionPrototype, args: Array<NylonFunction>): NylonFunction {
    return object : NylonFunction(prototype.string) {
        override fun apply(stack: NylonStack) {
            return prototype.apply(stack, args)
        }

        override fun toString(): String {
            var s = prototype.toString()
            for (arg in args) {
                s += "<$arg>"
            }
            return s
        }
    }
}

fun createProvider(nylonObject: NylonObject<*>): NylonFunction {
    return object : NylonFunction("Push($nylonObject)") {
        override fun apply(stack: NylonStack) {
            stack.push(nylonObject.clone())
        }
    }
}

fun concatenate(nylonFunctions: Collection<NylonFunction>): NylonFunction {
    return object : NylonFunction("Function($nylonFunctions)") {
        override fun apply(stack: NylonStack) {
            nylonFunctions.forEach { it.apply(stack) }
        }
    }
}

fun concatenate(vararg nylonFunctions: NylonFunction): NylonFunction {
    return object : NylonFunction("Function($nylonFunctions)") {
        override fun apply(stack: NylonStack) {
            nylonFunctions.forEach { it.apply(stack) }
        }
    }
}

fun emptyFunction(): NylonFunction = object : NylonFunction("Void") {
    override fun apply(t: NylonStack) {}
}
