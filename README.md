Nylon is stack based, interpreted, functional programming language designed to compete with Jelly, 05AB1E, MATL, and others in golfing challenges.

The Nylon interpreter can be used by downloading and running the jar via your OS' terminal. Use java -jar [jarpath] [filename] [library-path] to run a file with the Nylon interpreter.

Note that the interpreter is not yet finished, and any functionality described may not yet be implemented.

# Definitions

## Stacks

The Nylon language operates exclusively on stacks, with one stack allocated per function. Stacks in Nylon are represented as linked lists; pushing an object appends it to the linked list, and popping an object removes it.

## Constants

Nylon supports four constant types: doubles, characters, strings, and functions (Note that booleans can be stored using characters and strings, t/f & true/false respectively). Each constant can be pushed to the stack via a native function. When Nylon compiles, all constants in the source code are replaced with functions that take no arguments and push the constant to the stack. Constants can be created as follows:

Doubles:
<pre>
1
225.4234
3E199
</pre>

Characters:
<pre>
a
\?
</pre>

Strings:
<pre>
asdf
"????"
"\"?????\""
</pre>

Functions:
<pre>
@abcd
@+
@[+-*/]
</pre>

## Arguments
Arguments are variables that are passed to functions via the stack. Before the function is called, all arguments must be pushed to the stack that the function will read from.

Numeric arguments take both numbers and characters.

## Functions
Functions are declared by creating a new line in the Nylon code. Functions can be called using the 'ƒ' function, which
takes in an integer n and calls the function on line n.

Functions operate on a new stack that contains only the arguments passed to it. Functions contain a return stack which
is returned when the function terminates. If no function uses the return stack, the return stack is appended to the
stack of the function that called the function.

To require a number of arguments to call the function, precede the function definition with 0-9. Precede the function
with '.' to take all arguments from the stack. Precede the function with ',' to keep the function from creating a new
stack.

Functions will implicitly return all non-argument values on the stack.

A function can be pushed to stack to be accessed by other functions using '@'.

- ';': Pushes the top element of the function stack to the return stack.
- '!': Terminates the function.
- '[...]': Creates a function lambda.

Example 1:
<pre>
1ƒabc
2ƒ
abc

Output: "abcabc"
</pre>

Example 2:
<pre>
1ƒƒƒ
2
3
abc

Output: "abc"
</pre>

## If Statements
If Statements are functions that push a FunctionSkip object to the stack. If a FunctionSkip object is present when a function is called, the FunctionSkip object is removed and the function call is ignored. Normal functions may return a FunctionSkip object to emulate being an if statement.

- '¡': Pushes a FunctionSkip object to the stack.

Example 1:
<pre>
?abc

Input: "true", Output: "abc"
Input: "false", Output: ""
</pre>

Example 2:
<pre>
¡abc d

Output: "d"
</pre>

Example 3:
<pre>
1ƒa b c
¡

Output: "bc"
</pre>

## Switches
Switches will execute a function in a list of functions given an argument. If given a number or a character, it will
execute the nth function. If given a string, that string will be converted into a list of characters and the switch will
be executed for each one.

- '§f...]': Creates a switch with any number of functions.

Example:

<pre>
§5 4 3 2 1]a

Input: "0", Output: "5a"
Input: "2", Output: "3a"
Input: "4", Output: "1a"
Input: "5", Output: "a"
</pre>

## Loops
Loops iterate over a function. The return stacks of the function will not returned until the loop is finished executing, and will be concatenated before being returned. If the loop has a closing brace, the functions between the braces are implicitly converted into an lambda function to be called by the loop.

- '?(': Iterates for as long as a conditional is true. If the preceding token is not a conditional, and the last item on the stack is numeric, iterates n times instead, pushing n to the stack each loop.
- 's{': Iterates for each item in a stack or list. Defaults to the current stack.

Loops can also be passed arguments via characters. The arguments must be passed before the loop function.

- 'P': Start the loop from 1 instead of 0.
- 'I': Reverses the direction of the loop.

Example 1:
<pre>
@t(a:)

Output: "aaaaaaaaa..."
</pre>

Example 2:
<pre>
100()

Output: "012345679...99"
</pre>

Example 3:
<pre>
a b c{.}

Output: "abcabc"
</pre>

## Function Flags
The start of a function may contain any number of Function Flags. Rather than push a string to the stack, these flags change how certain things work. To push a string at the beginning of a function, precede the string with a space.

- 'i': Turns off implicit input.
- 'I': Turns on implicit input. (default)
- 'o': Turns off implicit output.
- 'O': Turns on implicit output. (default)
- 'r': Removes function arguments from the stack. (default)
- 'R': Keeps function arguments on the stack, rather than removing them.
- 'Z': The function flags for this function will affect sub functions (must be first flag).

## Aliases
Aliases are defined above the 0th function using a symbol followed by any number of characters. Before running the program, all instances of the symbol are replaced with the characters. Aliases are applied top to bottom, and are not applied on themselves. All lines containing aliases must start with '=', and any number of aliases may be on that line, delineated by spaces.

Example 1:
<pre>
=?123
???

Output: "123123123"
</pre>

Example 2:
<pre>
=1123
=@111
@

Output: "123123123"
</pre>

## Implicit Input
By default, when the program is called, all arguments are pushed onto the stack in order.

Example:
<pre>
.

Input: "3", Output: "33"
Input: "abc", Output: "abcabc"
</pre>

## Implicit Output
By default, when the function terminates, the function returns all consecutive matching objects starting from the top of the stack.

Example:
<pre>
123a b c

Output: "abc"
</pre>

# Builtin Functions

## Math Functions
- '+': Takes two arguments and concatenates them. If only one argument is present, increments it.
- '-': Takes two arguments and returns the difference. If only one argument is present, decrements it.
- '*': Takes two arguments and returns the product.
- '/': Takes two arguments and returns the quotient.
- '%': Takes two arguments and returns the remainder.
- '^': Takes two arguments and returns the power.

## If Statements
- '?': Takes an arguments and pushes a FunctionSkip object to the stack if it's false.
- '¿': Takes an arguments and pushes a FunctionSkip object to the stack if it's true.

## Others
- ':': Pops the top argument of the stack and prints it to stdout. If the top argument is a function, calls that function instead. If the stack is empty, pushes ':' to the stack.
- '.': Pushes the top argument of the stack.
- '|': Splits a String
- 'Ç': Casts a NylonObject to another NylonObject.
- 'µ': Ends the program.
