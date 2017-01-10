A list of libraries and functions contained in the Nylon Standard Library.

## Libraries

#### - C: Constants:
- Ce: Literal e = 2.718281828459045.
- Cg: Literal phi = 1.618033988749894.
- Ch: Literal 100.
- Cm: Literal 1000000.
- Cp: Literal pi = 3.141592653589793.
- Ct: Literal 1000.

#### - F: Files:
- Fa: Appends the top of the stack to a file.
- Fc: Creates a file with path equal to the top of the stack.
- Fd: Creates a directory with path equal to the top of the stack.
- Ff: Pushes true to the stack if the top of the stack is an existing, non-directory file.
- Fn: Creates a new file object from the top of the stack.
- Fw: Writes the top of the stack to a file.

#### - R: Random:
- Ra: Shuffles an array.
- Rb: Pushes a random boolean to the stack.
- Rd: Pushes a random double to the stack.
- Rl: Pushes a random long to the stack.
- Ru: Pushes a random unsigned long to the stack.

#### - U: Utilities:
- Ub: Returns the binary representation of a number as an array. // TODO
- Uo: Rotates an array.
- Up: Permutations of an array. // TODO
- Ur: Reverses an array.
- Us: Sorts an array.

## Misc. Functions
- c: Casts the top object of the stack to a character.
- d: Decrements the top item of the stack by 1.
- e: Adds all iterations of an object to the stack.
- i: Increments the top item of the stack by 1.
- m: Maps a function to an array.
- s: Swaps the top two items of the stack.
- t: Literal 10.
- v: Literal empty function.
- x: Clones the top two objects of the stack.
- z: Creates an array from the top n object of the stack.
- TESt: Runs unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is
likely broken. All updates to the Nylon repo must pass this test, or, if they change syntax, update the test to work with
the new syntax. All new standard library functions must add a line to the unit test to verify it working.
