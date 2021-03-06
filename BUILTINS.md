A list of builtins that are not discussed in README.md.

## Math
- '+': Adds the top two objects of the stack.
- '-': Subtracts the top two objects of the stack.
- '*': Multiplies the top two objects of the stack.
- '/': Divides the top two objects of the stack.
- '^': Exponentiates the top two objects of the stack.
- '%': Adds the remainder of the top two objects of the stack to the stack.

## Stack Manipulation
- '(': Shadows the current stack with a new stack.
- ')': Removes the current stack and replaces it with the shadowed one.
- 'À': Pulls the top argument from the shadowed stack to the current one.
- 'Á': Pushes the top argument from the current stack to the shadowed one.
- ':': Duplicates the top item of the stack.
- 'ù': Moves the top of the stack to the bottom.
- 'ú': Moves the bottom of the stack to the top.
- 'Ï': Pushes the length of the stack to the stack.

## Misc
- ',': Casts the top of the stack to a function, then calls it.
- '`': Writes the top object of the stack to stdout.
- 'ƒ': Compiles the top of the stack to a function.
- 'ð': "Paints" the top string of the stack on 4th string of the stack at coordinate points
(2rd on the stack, 3nd on the stack). "Painting" creates two grids from both of the strings, translates the string
to paint by (x, y), then overrides all characters on the string being painted over with the characters at the same
points as the paint.
<pre>
Example:
"|----|
|    |
|    |
|----|"
1 1
"1234
5678"ð
Output:
|----|
|1234|
|5678|
|----|
</pre>
