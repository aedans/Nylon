package nylon

import nylon.nylonobjects.CharObject
import nylon.parser.NylonParser
import java.io.PrintStream

/**
 * Created by Aedan Smith.
 */

class NylonRuntime(src: String, parser: NylonParser) : Runnable {
    val main = parser.parseAll(nylon.parser.CharIterator(src))
    val stack = NylonStack()

    override fun run() {
        main.apply(stack)
    }

    fun write(printStream: PrintStream) {
        stack.stacks.peek().forEach {
            if (it.shouldOutputNewline) {
                printStream.println(it.outputString())
            } else {
                printStream.print(it.outputString())
            }
        }
    }
}

private val <T> NylonObject<T>.shouldOutputNewline: Boolean
    get() = this !is CharObject
