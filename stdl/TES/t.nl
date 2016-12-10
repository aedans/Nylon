// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.

// Constant tests
'a'b'c'd'e          '
"abcde"             '

// STDL tests
'a'b'c'e'dc         '
'bd'cd'dd'ed'fd     '
"abcde"e            '
'`i'ai'bi'ci'di     '
'a'b'c'd'e5z        '

// C library

// U library
"edcba"Ur           '
"bcdea"Uo           '
"caedb"Us           '
