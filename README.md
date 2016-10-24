Nylon is stack based, interpreted, Functional programming language programmed in Java. It is designed to compete
with Jelly, 05AB1E, MATL, and others in golfing challenges. Nylon is designed to have small programs while still being
able to write complex programs easily, an ability that other golfing languages do not have.

# Definitions

## Functions
Functions are declared by creating a new line in the Nylon code. Functions can be called using "ƒn", where n is the line
on which the function is declared.

Functions operate on a new stack that contains only the arguments passed to it. Functions contain a return stack which
is returned when the function terminates. If no function uses the return stack, the return stack is appended to the
current stack.

To require a number of arguments to call the function, precede the function definition with 0-9. Precede the function
with '.' to take all arguments from the stack.

Functions will implicitly return all non-argument values on the stack.

- '[...]': Creates a new abstract function to be called by loops and if statements.
- ';': Pushes the function stack to the return stack.
- ':': Pushes the top element of the function stack to the return stack.
- '!': Terminates the function.

## Arguments
Arguments are passed to functions using "ƒa ". More than one argument must be delineated by a comma (eg. "ƒa,a,a "). If
the arguments are type-distinguishable (eg. Number,String), the comma can be removed.

If the argument is numeric, a-z may represent 0-25 and A-Z may represent 26-51. Numeric arguments also take numbers.
If the argument is a string, [a-zA-Z ] map to their respective characters. "..." delineates a String literal.

## If Statements
If Statements are functions that push a FunctionSkip object to the stack. If a FunctionSkip object is present when a
function is called, the FunctionSkip object is removed and the function call is ignored. Normal functions may return
a FunctionSkip object to emulate being an if statement.

- '¡': Pushes a FunctionSkip object to the stack.

## Switches
Switches will execute a function of a list of functions given an argument. If given a number or a character, it will
execute the nth function. If given a String, that string will be converted into a list of characters and the switch will
be executed for each one.

- '§f... ': Creates a switch with any number of functions.

## Loops
Loops iterate over a function. The return stacks of the function will not returned until the loop is finished executing,
and will be concatenated before being returned. If the loop has a closing brace, the functions inside the loop are
implicitly converted into an abstract function.

- '?(': Iterates for as long as the conditional is true. Conditional defaults to '?'. If the preceding token is numeric,
        or the last item on the stack is numeric, iterates n times instead, pushing and popping n from the stack each loop.
- 's{': Iterates for each item in a stack. Stack defaults to the current stack.

## Function Flags
The start of a function may contain any number of Function Flags ([a-zA-Z]). Rather than push a string to the stack, these
flags change how certain things work

- 'a': Turns off implicit output.
- 'A': Turns on implicit output. (default)
- 'i': Turns off implicit input.
- 'I': Turns on implicit input. (default)
- 'o': Turns off implicit output.
- 'O': Turns on implicit output. (default)
- 'R': Keeps function arguments on the stack, rather than removing them.
- 'r': Removes function arguments from the stack. (default)
- 'Z': The function flags for this function will affect sub functions (must be first flag).

## Aliases
Aliases are defined above the 0th function using a symbol followed by any number of characters. Before running the program,
all instances of the symbol are replaced with the characters. Aliases are applied top to bottom, and are not applied on
themselves. eAll lines containing aliases must start with '!', and any number of aliases may be on that line, separated by
spaces.

## GOTOs
- '^n': Moves to the nth function in the current function.

## Implicit Input
By default, when the program is called, all arguments are pushed onto the stack in order.

## Implicit Output
By default, all elements present when the program ends are printed to stdout.

## Implicit Arguments
By default, if a function is called without all required arguments, the function will take the rest of the arguments from 
the containing stack.

# Builtin Functions

### To be added soon
