package nylon

/**
 * Created by Aedan Smith.
 */

abstract class NylonFunction(val string: String, var argNum: Int = 0) {
    var args: Array<NylonFunction> = arrayOf()

    protected abstract fun apply(stack: NylonStack, args: Array<NylonFunction>)

    fun apply(stack: NylonStack) = apply(stack, args)

    fun apply(): NylonStack {
        val stack = NylonStack()
        apply(stack)
        return stack
    }

    fun clone(): NylonFunction {
        return object : NylonFunction(string, argNum) {
            override fun apply(stack: NylonStack, args: Array<NylonFunction>) = this@NylonFunction.apply(stack, args)
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
        override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
            stack.push(nylonObject.clone())
        }
    }
}

fun concatenate(nylonFunctions: Collection<NylonFunction>): NylonFunction {
    return object : NylonFunction("Function($nylonFunctions)") {
        override fun apply(stack: NylonStack, args: Array<NylonFunction>) {
            nylonFunctions.forEach { it.apply(stack) }
        }
    }
}

fun emptyFunction(): NylonFunction = object : NylonFunction("Void") {
    override fun apply(stack: NylonStack, args: Array<NylonFunction>) {}
}
