A list of builtins that are not discussed in README.md.

## Math
- '+': Adds the top two objects of the stack.
- '-': Subtracts the top two objects of the stack.
- '*': Multiplies the top two objects of the stack.
- '/': Divides the top two object of the stack.

## Stack Manipulation
- '(': Shadows the current stack with a new stack.
- ')': Removes the current stack and replaces it with the shadowed one.
- 'À': Pulls the top argument of the shadowed stack to the current one.
- 'Á': Pushes the top argument of the current stack to the shadowed one.
- '_': Creates a list and from the stack adds it to the stack.
- '$': Consumes all but the top item of the stack.
- '`': Consumes the top item of the stack.
- ':': Duplicates the top item of the stack.
- 'ù': Moves the top of the stack to the bottom.
- 'ú': Moves the bottom of the stack to the top.
- 'Ï': Pushes the length of the stack to the stack.
