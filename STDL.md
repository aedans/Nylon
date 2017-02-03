A list of libraries and functions contained in the Nylon Standard Library.

#### 1-char functions:
- c: Concatenates all items on the stack.
- d: Decrements the top item of the stack by 1.
- e: Adds all iterations of an object to the stack.
- f: Fills a list of size n with longs equal to the index
- i: Increments the top item of the stack by 1.
- l: Replaces the stack with a list containing the stack.
- m: Maps a function to a list.
- p: Pops the top object of the stack.
- s: Swaps the top two items of the stack.
- t: Literal 10.
- v: Literal empty list.
- x: Clones the top two objects of the stack.
- z: Creates a list from the top n object of the stack.

## Libraries

#### - C: Constants:
- Ca: Literal a-z
- Ce: Literal e = 2.718281828459045.
- Cg: Literal phi = 1.618033988749894.
- Ch: Literal 100.
- Cm: Literal 1000000.
- Cp: Literal pi = 3.141592653589793.
- Ct: Literal 1000.

#### - U: Utilities:
- Ur: Reverses a list.
- Us: Sorts a list.

## Misc. Functions
- ENDL: Literal newline.
- TEST: Runs unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is
likely broken. All updates to the Nylon repo must pass this test, or, if they change syntax, update the test to work with
the new syntax. All new standard library functions must add a line to the unit test to verify it working.
