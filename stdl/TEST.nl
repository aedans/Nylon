// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.
// All updates to the Nylon repo must pass these tests, or, if they change syntax, update this test to work with the new syntax.
// All new standard library functions must add a line to these tests to verify it working.

// Macro tests
#ENDL '

// Constant tests
'a'b'c'd'e                                  ENDL
"abcde"                                     ENDL
{"abcde"}                                   ENDL

// Casting tests
97~c98~c99~c100~c101~c                      ENDL

// Builtin tests
// Math
'2'/+'3'/+'4'/+'5'/+'6'/+                   ENDL
"abc""de"+                                  ENDL
101 4-~c101 3-~c101 2-~c101 1-~c101~c       ENDL
"abcdefg""fg"-                              ENDL
// TODO: Multiply strings
9 11*2-~c9 11*1-~c9 11*~c10 10*~c9 11*2+~c  ENDL
970 10/~c980 10/~c990 10/~c500 5/~c202 2/~c ENDL
10 2^3-~c10 2^2-~c10 2^1-~c10 2^~c10 2^1+~c ENDL
// TODO: Modulo test

// Stack
"abc"(À"de"+Á)                              ENDL
('a'b'c'd'eaÁ)~s                            ENDL
('b'c'd'e'aùaÁ)~s                           ENDL
('e'a'b'c'dúaÁ)~s                           ENDL
// TODO: Length of stack builtin

// Functions
@"abcde",                                   ENDL

// Ascii Canvas
"ab de"0 2'cð                               ENDL

// If statement tests
1?"abcde"!"ERROR"                           ENDL
0¿"abcde"!"ERROR"                           ENDL
5 4>{p"abcde"}!{p"ERROR"}                   ENDL
4 5<{p"abcde"}!{p"ERROR"}                   ENDL
3 3={p"abcde"}!{p"ERROR"}                   ENDL
3 3<={p"abcde"}!{p"ERROR"}                  ENDL
3 3=¿{p"abcde"}!{p"ERROR"}                  ENDL
3 0?>{p"abcde"}!{p"ERROR"}                  ENDL

// For loop tests
5î{'a+~c}                                   ENDL
('aìi~cÁ'bìi~cÁ'cìi~cÁ'dìi~cÁ'eìi~cÁ)       ENDL
0'aíi~c1'aíi~c2'aíi~c3'aíi~c4'aíi~c         ENDL
'aï{}'bï{}'cï{}'dï{}'eï{}                   ENDL

// STDL tests
// a.nl and c.nl are tested in the stack section. p.nl is tested in the if statement section.
'bd'cd'dd'ed'fd                             ENDL // d.nl
"abcde"e                                    ENDL // e.nl
'`i'ai'bi'ci'di                             ENDL // i.nl
"`abcd"@ime                                 ENDL // m.nl
'a'b'c'e'ds                                 ENDL // s.nl
tt*3-~ctt*2-~ctt*1-~ctt*~ctt*1+~c           ENDL // t.nl
"abc"exis3+                                 ENDL // x.nl
'a'b'c'd'e5z~s                              ENDL // z.nl

// C library: No tests.

// F Library: No tests, due to non-deterministic output.

// R Library: No tests, due to non-deterministic output.

// U library
"edcba"Ur~s                                 ENDL
"bcdea"Uo~s                                 ENDL
"caedb"Us~s                                 ENDL
