// #Interpreter Function
// Creates a new NylonFile with path equal to the top of the stack.
//
// NylonFile Semantics:
//
// When cast to a boolean, returns whether or not the file exists.
// When cast to a double, returns the size of the file.
// When passed to a for loop:
//     If the file is a directory, iterates once for each file in the directory.
//     If the file is a file, iterates once for each line in the file.
// When cast to an array:
//     If the file is a directory, creates an array of all files in the directory
//     If the file is a file, creates an array of all lines in the file.

"Interpreter does not support NylonFile type" // If the function is not implemented