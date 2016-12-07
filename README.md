Nylon is stack based functional programming language designed to compete with Jelly, 05AB1E, MATL, and others in golfing challenges.

The Nylon interpreter can be used by downloading and running the jar via your OS' terminal. Use java -jar [jarpath] [filename] [library-path] to run a file with the Nylon interpreter.

If you are running Windows, an installer can be found in the WinInstall directory. Running the installer as administrator will create a directory at C:\Nylon containing the nylon.bat command and add it to the CMD PATH.

Note that the interpreter is not yet finished, and any functionality described may not yet be implemented.

See tkaden4/cylon for a C implementation of Nylon.

# Definitions

## Stacks

The Nylon language operates exclusively on stacks, with one stack allocated per function. Stacks in Nylon are represented as linked lists; pushing an object appends it to the linked list, and popping an object removes it.

## Constants

Constants in Nylon are functions that take no arguments and push a constant to the stack. There are four constant types: Doubles, characters, stacks, and functions. Booleans are be represented using 0/1. Constants are declared as follows:

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

Stacks:
<pre>
{1.2 2344 134}
{'a'b'c'd'e'f'g}
{[abcd][2+][+-*/]}
{{"abc"}{123}{[{}]}
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

If statements are function modifiers that cause a function to be applied if the if statement is true. There are four if statements in Nylon, '?', '¿', '>', '<', '=', and '!'. If the statement is true, the following function is executed, otherwise it is skipped. For successive if statements, any of the statements must be true for the following function to execute.

- '?': If the top of the stack is true.
- '¿': If the top of the stack is false.
- '>': If the second from the top of the stack is greater than the top.
- '<': If the second from the top of the stack is less than the top.
- '=': If the top of the stack is equal to the second from the top.
- '!': If the top of the stack is not equal to the second from the top.

Example 1
<pre>
="true"!"false"

Yields true if the inputs are equal, false otherwise.
</pre>

Example 2
<pre>
>="more"!"less"

Yields true if the second input is greater than or equal to the first input, false otherwise.
</pre>

## While Loops

While loops are function modifiers that cause a function to be called as long as a condition is true. While loops are created using '&', followed by a comparator.

## For Loops

For loops are function modifiers that cause a function to be called multiple times, each time enumerating different arguments. For loops are created using '%'. When a for loop is called, it consumes the top object of the stack and behaves differently depending on the type of object consumed.

- [double|character]: Treated as a for loop from 0 to the number.
- [function]: Iterates once for each object returned by the function.
- [stack]: Iterates once for each object in the stack.

## Standard Library

The Nylon Standard Library contains many useful functions for golfing. Functions can be accessed using any sequence of alphabetic letters. Explanations of methods in the Nylon Standard Library are described in STDL.md.

## Builtins

Nylon has many builtins allowing for functions that may not otherwise be possible. Builtins are always single characters. A list of builtins and their descriptions can be found in BUILTINS.md.
