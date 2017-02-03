import nylon.NylonRuntime
import nylon.buildLibrary
import nylon.parser.NylonParser
import java.io.File
import java.io.FileReader

/**
 * Created by Aedan Smith.
 */

fun main(args: Array<String>) {
    val parser = NylonParser()
    buildLibrary(parser, File(args[0]))
    val runtime = NylonRuntime(FileReader(args[1]).readText(), parser)
    runtime.run()
    runtime.write(System.out)
}
