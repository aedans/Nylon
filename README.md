Nylon is stack based functional programming language designed to compete with Jelly, 05AB1E, MATL, and others in golfing challenges.

The Nylon interpreter can be used by downloading and running the jar via your OS' terminal. Use java -jar [jarpath] [filename] [library-path] to run a file with the Nylon interpreter.

If you are running Windows, an installer can be found in the WinInstall directory. Running the installer as administrator will create a directory at C:\Nylon containing the nylon.bat command and add it to the CMD PATH.

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
[abcd]
[2+]
[+-*/]
</pre>

## Variables
The Nylon Runtime contains variables that can be accessed and modified using the '&' and '$' functions.

- "[string]&": Pushes the variable with the given name to the stack.
- "[object] [string]$": Creates/overwrites a variable with the given name and value.

Example 1:
<pre>
10i$ i&

Output: "10"
</pre>

Example 2:
<pre>
@[abcd]f$ f&

Output: "abcd"
</pre>

## Arguments
Arguments are variables that are passed to functions via the stack. Before the function is called, all arguments must be pushed to the stack that the function will read from.

Numeric arguments take both numbers and characters.

## Functions
Functions are declared using "[...]". A function can be called by storing it in a variable and using the 'ƒ' function.

Functions operate on a new stack that contains only the arguments passed to it. Functions contain a return stack which is returned when the function terminates. If no function uses the return stack, the return stack is appended to the stack of the function that called the function.

Functions will implicitly return all non-argument values on the stack.

- '[string]ƒ': Calls the function with the given name.
- ';': Pushes the top element of the function stack to the return stack.
- '!': Terminates the function.
- "[...]": Creates a function.

Example 1:
<pre>
[abc]l$lƒlƒ

Output: "abcabc"
</pre>

Example 2:
<pre>
[+]add$2 3addƒ4addƒ

Output: "9"
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

- "[conditional](": Iterates for as long as a conditional is true.
- "[double](": Iterates n times, pushing n to the stack each loop.
- "[list](": Iterates once for each item in the list.
- "[string](": Iterates once for each character in the string.
- "[function](": Iterates once for each item in the return stack of the function.

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
- "[double]+": Increments a double.
- "[object] [object]+": Concatenates two objects.
- "[double]-": Decrements a double.
- "[double] [double]-": Returns the difference of two doubles.
- "[double] [double]*": Returns the product of two doubles.
- "[double] [double]/": Returns the quotient of two doubles.
- "[double] [double]%": Returns the remainder of two doubles.
- "[double] [double]^": Returns the power of two doubles.

## If Statements
- "[object]?": Pushes a FunctionSkip object to the stack if an object is false.
- "[object]¿": Pushes a FunctionSkip object to the stack if an object is true.

## Lists
- '†': Creates a new list.
- "[function]†": Creates a new list from the return stack of the function.
- '‡': Creates a list from the current stack.

## IO
- "[object]:": Prints an object to stdout.
- 'š': Returns a line of input from stdin.

## Others
- ':': Returns the ':' character.
- ';': Returns the ';' character.
- '[object];': Returns the object.
- "[function]:": Calls a function.
- "[string]|": Splits a String
- "[object] [character]Ç": Casts a NylonObject to another NylonObject.
- 'µ': Ends the program.
- 'ð': Pushes 10 to the stack.
- 'Ð': Pushes 1000 to the stack.
