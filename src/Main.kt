import nylon.NylonRuntime
import nylon.buildLibrary
import nylon.nylonobjects.StringObject
import nylon.parser.NylonParser
import nylon.parser.readExtendedText
import java.io.File
import java.io.FileReader

/**
 * Created by Aedan Smith.
 */

fun main(args: Array<String>) {
    val parser = NylonParser()
    buildLibrary(parser, File(args[0]))
    val time = System.nanoTime()
    val runtime = NylonRuntime(nylon.parser.CharIterator(FileReader(args[1]).readExtendedText()), parser)
    (2..args.size - 1).forEach { i -> runtime.stack.add(StringObject(args[i])) }
    println("Program compiled in ${(System.nanoTime() - time).toDouble() / 1000000.0} milliseconds\n")
    runtime.run()
    runtime.write(System.out)
}
