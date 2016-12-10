// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.

// Constant tests
'a'b'c'd'e                                  '
"abcde"                                     '
["abcde"]                                   '

// Casting tests
// TODO: d&c->l, l->d&c
97~c98~c99~c100~c101~c                      '
97~f~c98~f~c99~f~c100~f~c101~f~c            '
"\"abcde"~f~l                               // No ' here because the list should print a trailing newline.

// Builtin tests
// Math
'2'/+'3'/+'4'/+'5'/+'6'/+                   '
"abc""de"+                                  '
101 4-~c101 3-~c101 2-~c101 1-~c101~c       '
"abcdefg""fg"-                              '
// TODO: Multiply strings.
9 11*2-~c9 11*1-~c9 11*~c10 10*~c9 11*2+~c  '
970 10/~c980 10/~c990 10/~c500 5/~c202 2/~c '

// Stack
"abc"(À"de"+Á)                              '
('a'b'c'd'e_$Á)                             '
"abcde":`                                   '
('b'c'd'e'aù_$Á)                            '
('e'a'b'c'dú_$Á)                            '
// TODO: Length of stack builtin

// If statement tests
1?[`"abcde"]![`"ERROR"]                     '
0¿[`"abcde"]![`"ERROR"]                     '
5 4>[``"abcde"]![``"ERROR"]                 '
4 5<[``"abcde"]![``"ERROR"]                 '
3 3=[``"abcde"]![``"ERROR"]                 '
3 3<=[``"abcde"]![``"ERROR"]                '
3 3=¿[``"abcde"]![``"ERROR"]                '
3 0?>[``"abcde"]![``"ERROR"]                '

// For loop tests
5î['a+c]                                    '
('aìi~cÁ'bìi~cÁ'cìi~cÁ'dìi~cÁ'eìi~cÁ)       '
0'aíi~c1'aíi~c2'aíi~c3'aíi~c4'aíi~c         '
'aï[]'bï[]'cï[]'dï[]'eï[]                   '

// STDL tests
97c98c99c100c101c                           '
'bd'cd'dd'ed'fd                             '
"abcde"e                                    '
'`i'ai'bi'ci'di                             '
'a'b'c'e'ds                                 '
tt*3-ctt*2-ctt*1-ctt*ctt*1+c                '
'a'b'c'd'e5z                                '

// C library: No tests.

// U library
"edcba"Ur                               '
"bcdea"Uo                               '
"caedb"Us                               '