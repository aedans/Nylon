// Unit tests for the Nylon interpreter. If a line does not output the string "abcde", then the interpreter is likely broken.
// All updates to the Nylon repo must pass these tests, or, if they change syntax, update this test to work with the new syntax.
// All new standard library functions must add a line to these tests to verify it working.

'a'b'c'd'e                              ENDL
"abcde"
{"abcde"}
97â98â99â100â101â                       ENDL
'2'/+'3'/+'4'/+'5'/+'6'/+               ENDL
"abc""de"+
101 4-â101 3-â101 2-â101 1-â101â        ENDL
"abcdefg""fg"-
9 11*2-â9 11*1-â9 11*â10 10*â9 11*2+â   ENDL
970 10/â980 10/â990 10/â500 5/â202 2/â  ENDL
10 2^3-â10 2^2-â10 2^1-â10 2^â10 2^1+â  ENDL
"abc"(À"de"+Á)
('a'b'c'd'elÁ)ã
('b'c'd'e'aùlÁ)ã
('e'a'b'c'dúlÁ)ã
@"abcde",
1?"abcde"!"ERROR"
0¿"abcde"!"ERROR"
5 4>{p"abcde"}!{p"ERROR"}
4 5<{p"abcde"}!{p"ERROR"}
3 3={p"abcde"}!{p"ERROR"}
3 3<={p"abcde"}!{p"ERROR"}
3 3=¿{p"abcde"}!{p"ERROR"}
3 0?>{p"abcde"}!{p"ERROR"}
5î{'a+â}                                ENDL
('aìiâÁ'bìiâÁ'cìiâÁ'dìiâÁ'eìiâÁ)        ENDL
0'aíiâ1'aíiâ2'aíiâ3'aíiâ4'aíiâ          ENDL
'aï{}'bï{}'cï{}'dï{}'eï{}               ENDL
'bd'cd'dd'ed'fd                         ENDL
"abcde"e                                ENDL
'`i'ai'bi'ci'di                         ENDL
"`abcd"mie                              ENDL
'a'b'c'e'ds                             ENDL
tt*3-âtt*2-âtt*1-âtt*âtt*1+â            ENDL
"abc"exis3+                             ENDL
'a'b'c'd'e5zã
"edcba"Urã
"caedb"Usã
