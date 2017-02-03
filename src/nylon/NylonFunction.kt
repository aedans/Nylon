package nylon

import java.util.*
import java.util.function.Supplier

/**
 * Created by Aedan Smith.
 */

abstract class NylonFunction(val string: String, var argNum: Int = 0) {
    private var args: ArrayList<NylonFunction> = ArrayList(argNum)

    protected abstract fun apply(stack: NylonStack, args: ArrayList<NylonFunction>)

    fun apply(stack: NylonStack) = apply(stack, args)

    fun apply(): NylonStack {
        val stack = NylonStack()
        apply(stack)
        return stack
    }

    open fun resolveArgs(argumentSupplier: Supplier<NylonFunction>) {
        while (args.size < argNum) {
            args.add(argumentSupplier.get())
        }
    }

    fun resolveNestedArgs(argumentSupplier: Supplier<NylonFunction>) {
        resolveArgs(argumentSupplier)
        args.forEach { it.resolveNestedArgs(argumentSupplier) }
    }

    fun clone(): NylonFunction {
        return object : NylonFunction(string, argNum) {
            override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) = this@NylonFunction.apply(stack, args)
        }
    }

    override fun toString(): String {
        var s = string
        args.forEach { s += "<$it>" }
        return s
    }
}

fun createProvider(nylonObject: NylonObject<*>): NylonFunction {
    return object : NylonFunction("Push($nylonObject)") {
        override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
            stack.push(nylonObject.clone())
        }
    }
}

fun concatenate(nylonFunctions: Collection<NylonFunction>): NylonFunction {
    return object : NylonFunction("Function") {
        override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
            nylonFunctions.forEach { it.apply(stack) }
        }

        override fun resolveArgs(argumentSupplier: Supplier<NylonFunction>) {
            super.resolveArgs(argumentSupplier)
            nylonFunctions.forEach { it.resolveNestedArgs(argumentSupplier) }
        }

        override fun toString(): String {
            var s = string + "("
            s += nylonFunctions.toString()
            return s + ")"
        }
    }
}

fun emptyFunction(): NylonFunction = object : NylonFunction("Void") {
    override fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {}
}
