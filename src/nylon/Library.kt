package nylon

import nylon.builtins.CreateListFromStack
import nylon.parser.NylonParser
import nylon.parser.readExtendedText
import java.io.File
import java.io.FileReader
import java.util.function.Supplier

/**
 * Created by Aedan Smith.
 */

fun buildLibrary(parser: NylonParser, library: File) {
    build(parser, library, "")

    parser.functions.put("l", Supplier { CreateListFromStack() })
}

private fun build(parser: NylonParser, library: File, path: String) {
    for (file: File in library.listFiles()) {
        if (file.isDirectory) {
            build(parser, file, path + file.name)
        } else {
            parser.functions.put(
                    path + file.name.substring(0, file.name.length - 3),
                    LibraryFunctionSupplier(parser, file)
            )
        }
    }
}

class LibraryFunctionSupplier(parser: NylonParser, file: File) : Supplier<NylonFunction> {
    val function: NylonFunction by lazy {
        parser.parseAll(FileReader(file).readExtendedText())
    }

    override fun get() = function.clone()
}
