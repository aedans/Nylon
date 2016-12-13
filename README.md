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

Constants in Nylon are functions that take no arguments and push a constant to the stack. There are four constant types:
Doubles, characters, strings, and functions. Booleans are be represented using 0/1. Constants are declared as follows:

Doubles:
<pre>
1
225.4234
3E199
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
execute. '!' is used as an effective else for if statements.

- '?': If the top of the stack is true.
- '¿': If the top of the stack is false.
- '>': If the second from the top of the stack is greater than the top.
- '<': If the second from the top of the stack is less than the top.
- '=': If the top of the stack is equal to the second from the top.

## While Loops

While loops are function modifiers that cause a function to be called as long as a condition is true. While loops are
created using '&', followed by a comparator.

## For Loops

For loops are function modifiers that cause a function to be called multiple times, each time enumerating different
arguments. When a for loop is called, it reads the top object of the stack and behaves differently depending on the type
of object consumed.

- [double|character]: Treated as a for loop from 0 to the number.
- [function]: Iterates once for each object returned by the function.
- [list]: Iterates once for each object in the list.

There are four types of for loops, one for each permutation of consuming the top of the stack and pushing the current
loop value.

- 'î': Creates a consuming, pushing for loop.
- 'ì': Creates a non-consuming, pushing for loop.
- 'í': Creates a consuming, non-pushing for loop.
- 'ï': Creates a non-consuming, non-pushing for loop.

## Casting
Casting is done via the character '~', followed by 'd' (double), 'c', (character), 'l' (list), or 'f' (function). Casting
casts the top object of the stack to the given type, and each type has unique methods of casting.

- d->c: The UTF-16 character with value d.
- d->l: A list containing d.
- d->f: A function that pushes d to the stack.
- c->(anything): Creates a double with value c, then casts that to the target.
- l->d|c: A double or character equal to the size of the list.
- l->f: Attempts to create a string from the list, then compiles the string.
- f->(anything): Calls the function, then get the return value and casts that to the target.

## Standard Library

The Nylon Standard Library contains many useful functions for golfing. Functions can be accessed using any sequence of
alphabetic letters. Capital letters declare the library, while lowercase letters declare the function. Function names
must be one single lowercase letter, but library names may be any number of capital letters. This capitalization allows
the interpreter to parse statements like "UsUreUoc" without needing separators.

Explanations of methods in the Nylon Standard Library are described in STDL.md.

## Builtins

Nylon has many builtins that are not described here, allowing for functions that may not otherwise be possible. A list
of builtins and their descriptions can be found in BUILTINS.md.
