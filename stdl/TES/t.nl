// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.
// All updates to the Nylon repo must pass these tests, or, if they change syntax, update this test to work with the new syntax.
// All new standard library functions must add a line to these tests to verify it working.

// Constant tests
'a'b'c'd'e                                  '
"abcde"                                     '
["abcde"]                                   '

// Casting tests
97~c98~c99~c100~c101~c                      '

// Builtin tests
// Math
'2'/+'3'/+'4'/+'5'/+'6'/+                   '
"abc""de"+                                  '
101 4-~c101 3-~c101 2-~c101 1-~c101~c       '
"abcdefg""fg"-                              '
// TODO: Multiply strings
9 11*2-~c9 11*1-~c9 11*~c10 10*~c9 11*2+~c  '
970 10/~c980 10/~c990 10/~c500 5/~c202 2/~c '
10 2^3-~c10 2^2-~c10 2^1-~c10 2^~c10 2^1+~c '
// TODO: Modulo test

// Stack
"abc"(À"de"+Á)                              '
('a'b'c'd'e_Á)~s                            '
('b'c'd'e'aù_Á)~s                           '
('e'a'b'c'dú_Á)~s                           '
// TODO: Length of stack builtin

// Functions
"\"abcde
",

// Ascii Canvas
"ab de"0 2'c$                               '

// If statement tests
1?[;"abcde"]![;"ERROR"]                     '
0¿[;"abcde"]![;"ERROR"]                     '
5 4>[;;"abcde"]![;;"ERROR"]                 '
4 5<[;;"abcde"]![;;"ERROR"]                 '
3 3=[;;"abcde"]![;;"ERROR"]                 '
3 3<=[;;"abcde"]![;;"ERROR"]                '
3 3=¿[;;"abcde"]![;;"ERROR"]                '
3 0?>[;;"abcde"]![;;"ERROR"]                '

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
'a'b'c'd'e5z~s                              '

// C library: No tests.

// F Library: No tests, due to non-deterministic output.

// R Library: No tests, due to non-deterministic output.

// U library
"edcba"Ur~s                                 '
"bcdea"Uo~s                                 '
"caedb"Us~s                                 '
