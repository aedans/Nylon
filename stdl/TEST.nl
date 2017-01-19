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
97â98â99â100â101â                           ENDL

// Builtin tests
// Math
'2'/+'3'/+'4'/+'5'/+'6'/+                   ENDL
"abc""de"+                                  ENDL
101 4-â101 3-â101 2-â101 1-â101â            ENDL
"abcdefg""fg"-                              ENDL
// TODO: Multiply strings
9 11*2-â9 11*1-â9 11*â10 10*â9 11*2+â       ENDL
970 10/â980 10/â990 10/â500 5/â202 2/â      ENDL
10 2^3-â10 2^2-â10 2^1-â10 2^â10 2^1+â      ENDL
// TODO: Modulo test

// Stack
"abc"(À"de"+Á)                              ENDL
('a'b'c'd'eaÁ)ã                             ENDL
('b'c'd'e'aùaÁ)ã                            ENDL
('e'a'b'c'dúaÁ)ã                            ENDL
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
5î{'a+â}                                    ENDL
('aìiâÁ'bìiâÁ'cìiâÁ'dìiâÁ'eìiâÁ)            ENDL
0'aíiâ1'aíiâ2'aíiâ3'aíiâ4'aíiâ              ENDL
'aï{}'bï{}'cï{}'dï{}'eï{}                   ENDL

// STDL tests
// a.nl and c.nl are tested in the stack section. p.nl is tested in the if statement section.
'bd'cd'dd'ed'fd                             ENDL // d.nl
"abcde"e                                    ENDL // e.nl
'`i'ai'bi'ci'di                             ENDL // i.nl
"`abcd"@ime                                 ENDL // m.nl
'a'b'c'e'ds                                 ENDL // s.nl
tt*3-âtt*2-âtt*1-âtt*âtt*1+â                ENDL // t.nl
"abc"exis3+                                 ENDL // x.nl
'a'b'c'd'e5zã                               ENDL // z.nl

// C library: No tests.

// F Library: No tests, due to non-deterministic output.

// R Library: No tests, due to non-deterministic output.

// U library
"edcba"Urã                                  ENDL
"bcdea"Uoã                                  ENDL
"caedb"Usã                                  ENDL
