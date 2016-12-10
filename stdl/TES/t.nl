// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.

// Constant tests
'a'b'c'd'e                          '
"abcde"                             '
["abcde"]                           '

// If statement tests
1?[`"abcde"]![`"ERROR"]             '
0¿[`"abcde"]![`"ERROR"]             '
5 4>[``"abcde"]![``"ERROR"]         '
4 5<[``"abcde"]![``"ERROR"]         '
3 3=[``"abcde"]![``"ERROR"]         '
3 3<=[``"abcde"]![``"ERROR"]        '
3 3=¿[``"abcde"]![``"ERROR"]        '
3 0?>[``"abcde"]![``"ERROR"]        '

// Casting tests
// TODO: d&c->l, l->d&c
97~c98~c99~c100~c101~c              '
97~f~c98~f~c99~f~c100~f~c101~f~c    '
"\"abcde"~f~l                       // No ' here because the list should print a trailing newline.

// STDL tests
97c98c99c100c101c                   '
'bd'cd'dd'ed'fd                     '
"abcde"e                            '
'`i'ai'bi'ci'di                     '
'a'b'c'e'ds                         '
'a'b'c'd'e5z                        '

// C library

// U library
"edcba"Ur                           '
"bcdea"Uo                           '
"caedb"Us                           '
