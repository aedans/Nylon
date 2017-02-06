import nylon.NylonRuntime
import nylon.buildLibrary
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
    val runtime = NylonRuntime(FileReader(args[1]).readExtendedText(), parser)
    System.out.printf("Program compiled in %f milliseconds\n", (System.nanoTime() - time).toDouble() / 1000000.0)
    runtime.run()
    runtime.write(System.out)
}
