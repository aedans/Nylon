package nylon

import nylon.builtins.*
import nylon.parser.NylonParser

/**
 * Created by Aedan Smith.
 */

fun buildBuiltins(parser: NylonParser) {
    CaptureBuiltinBuilder().accept(parser.parsers)

    ForîLoopBuiltinParserBuilder().accept(parser.parsers)
    ForìLoopBuiltinParserBuilder().accept(parser.parsers)
    ForíLoopBuiltinParserBuilder().accept(parser.parsers)
    ForïLoopBuiltinParserBuilder().accept(parser.parsers)
    WhileLoopBuiltinParser().accept(parser.parsers)

    CastToDoubleParserBuilder().accept(parser.parsers)
    CastToLongParserBuilder().accept(parser.parsers)
    CastToCharParserBuilder().accept(parser.parsers)
    CastToListParserBuilder().accept(parser.parsers)
    CastToStringParserBuilder().accept(parser.parsers)
    CastToFunctionParserBuilder().accept(parser.parsers)

    AdditionParserBuilder().accept(parser.parsers)
    SubtractionParserBuilder().accept(parser.parsers)
    MultiplicationParserBuilder().accept(parser.parsers)
    DivisionParserBuilder().accept(parser.parsers)
    ExponentParserBuilder().accept(parser.parsers)
    ModParserBuilder().accept(parser.parsers)

    PushStackParserBuilder().accept(parser.parsers)
    PopStackParserBuilder().accept(parser.parsers)
    PullTopParserBuilder().accept(parser.parsers)
    PushTopParserBuilder().accept(parser.parsers)
    CloneParserBuilder().accept(parser.parsers)
    MoveToBottomParserBuilder().accept(parser.parsers)
    MoveToTopParserBuilder().accept(parser.parsers)
    LengthOfStackParserBuilder().accept(parser.parsers)

    CallFunctionParserBuilder().accept(parser.parsers)
    PrintParserBuilder().accept(parser.parsers)
    AsciiCanvasParserBuilder().accept(parser.parsers)
    ParserParserBuilder().accept(parser.parsers)
}
