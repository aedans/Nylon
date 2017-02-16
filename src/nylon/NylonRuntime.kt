package nylon

import nylon.nylonobjects.CharObject
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import java.io.PrintStream

/**
 * Created by Aedan Smith.
 */

class NylonRuntime(src: CharIterator, parser: NylonParser) : Runnable {
    val main = parser.parseAll(src)
    val stack = NylonStack()

    override fun run() {
        main.apply(stack)
    }

    fun write(printStream: PrintStream) {
        for (it in stack.stacks.peek()) {
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
