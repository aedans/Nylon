package nylon.parser.parsers

import nylon.NylonFunction
import nylon.createProvider
import nylon.nylonobjects.DoubleObject
import nylon.nylonobjects.LongObject
import nylon.parser.CharIterator
import nylon.parser.NylonParser
import nylon.parser.Parser
import nylon.parser.ParserBuilder
import java.util.*

/**
 * Created by Aedan Smith.
 */

class NumberParserBuilder : ParserBuilder {
    override fun accept(parsers: ArrayList<Parser>) {
        for (i in '0'..'9') {
            parsers[i.toInt()] = NumberParser()
        }
    }
}

class NumberParser : Parser {
    override fun apply(src: CharIterator, parser: NylonParser): NylonFunction? {
        var num = ""
        var isDouble = false
        while (src.hasNext() && (src.peek() in '0'..'9' || src.peek() == '.')) {
            if (src.peek() == '.')
                isDouble = true
            num += src.next()
        }
        if (isDouble) {
            return createProvider(DoubleObject(num.toDouble()))
        } else {
            return createProvider(LongObject(num.toLong()))
        }
    }
}
