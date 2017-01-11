Nylon is stack based functional programming language designed to compete with Jelly, 05AB1E, MATL, and others in golfing
challenges.

The Nylon interpreter can be used by downloading and running the jar via your OS' terminal. Use
java -jar [jarpath] [library-path] [filename] to run a file with the Nylon interpreter.

Also see tkaden4/Cylon for a C implementation of Nylon.

# Definitions

## Stacks

The Nylon language operates exclusively on one stack. The stack in Nylon is represented as a linked list; pushing an
object appends it to the linked list, and popping an object removes it. This means that variables do not have to be
declared in order to be accessed; they simply have to be pushed to the top of the stack. However, this does make memory
management difficult, as objects can be lost in the stack if the program is not well designed.

## Constants

Constants in Nylon are functions that take no arguments and push a constant to the stack. There are five constant types:
Doubles, longs, characters, strings, and functions. Booleans are be represented using 0/1. Constants are declared as
follows:

Longs:
<pre>
42
25234
25E8
</pre>

Doubles:
<pre>
1.0
225.4234
3E199
144.
</pre>

Characters:
<pre>
'a
'?
</pre>

Strings:
<pre>
"abcdefg"
"123\""
</pre>

Functions:
<pre>
[abcd]
[2+]
[+-*/]
</pre>

Standard Library Functions:
<pre>
r
Rs
</pre>

## If Statements

If statements are function modifiers that cause a function to be applied if the if statement is true. There are five if
statements in Nylon, '?', '¿', '>', '<', and '='. If the statement is true, the following function is executed,
otherwise it is skipped. For successive if statements, any of the statements must be true for the following function to
execute. '!' is used as an effective else for if statements. If statements will always consume the top object of the
stack. To preserve the top object of the stack, add a '!' as the last character of the if statement.

- '?': If the top of the stack is true.
- '¿': If the top of the stack is false.
- '>': If the second from the top of the stack is greater than the top.
- '<': If the second from the top of the stack is less than the top.
- '=': If the top of the stack is equal to the second from the top.

## For Loops

For loops are function modifiers that cause a function to be called multiple times, each time enumerating different
arguments. When a for loop is called, it reads the top object of the stack and behaves differently depending on the type
of object consumed.

- [number]: Treated as a for loop from 0 to the number.
- [function]: Iterates once for each object returned by the function.
- [array]: Iterates once for each object in the array.

There are four types of for loops, one for each permutation of consuming the top of the stack and pushing the current
loop value.

- 'î': Creates a consuming, pushing for loop.
- 'ì': Creates a non-consuming, pushing for loop.
- 'í': Creates a consuming, non-pushing for loop.
- 'ï': Creates a non-consuming, non-pushing for loop.

## Casting
Casting is done via the character '~', followed by 'a' (array), 'd' (double), 'l' (long), 'c', (character), 'b' (boolean),
's' (string), or 'f' (function). Casting casts the top object of the stack to the given type, and each type has unique
methods of casting.

Defaults:
- (anything) -> a: Creates a 1-element array containing the object.
- (anything) -> l: Casts the object to a double, then casts that to a long.
- (anything) -> c: Casts the object to a double, then casts that to a char.
- (anything) -> b: Casts the object to a double, then casts that to a boolean.
- (anything) -> f: Creates a function that pushes clones of the object to the stack.

Special:
- a -> d: Creates a double with size equal to the length of the array.
- a -> b: False if the last element of the array is false, or if the array is empty.
- b -> d: 1 if true, 0 if false.
- s -> d: Attempts to parse the string to a double.
- s -> f: Compiles the string to function as though it were the source.

## Standard Library

The Nylon Standard Library contains many useful functions for golfing. Functions can be accessed using any sequence of
alphabetic letters. Capital letters declare the library, while lowercase letters declare the function. Function names
must be one single lowercase letter, but library names may be any number of capital letters. This capitalization allows
the interpreter to parse statements like "UsUreUoc" without needing separators.

Many standard library functions, such as "Fn" (File New) are implemented in the interpreter. These functions start
with the line "// #Interpreter Function"; the rest of the function — usually an error message — should be ignored.

Explanations of methods in the Nylon Standard Library are described in STDL.md.

## Builtins

Nylon has many builtins that are not described here, allowing for functions that may not otherwise be possible. A list
of builtins and their descriptions can be found in BUILTINS.md.

## Captures
The capture builtin '@' captures the next function in the source code and pushes it to the stack. This can be used to
pass functions to other functions, call the function multiple times, and many other usages.

Captures can also be used to debug Nylon programs. When a function capture is cast to a string, it creates a readable
representation of the function.

Example:
<pre>
@d

Output:
LibraryFunction("d.nl")[
	PushNylonDouble("1.0"),
	BuiltinFunction('-')
]
</pre>

## Function Macros

Function macros can be defined using '#', followed by the function name and the function to call. The name may include
capital and lowercase letters, '-', and '_'. Functions macros can be called by inserting the name at any point in the
program.

Note that function macros can override standard library functions. To make sure that your names do not override any
standard library functions, all macros should be defined in all caps.

Example:
<pre>
#HELLO "Hello, "

#WORLD [
    "world!"
]

HELLO WORLD

Output:
Hello, world!
</pre>

