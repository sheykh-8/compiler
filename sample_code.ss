


Benevis("salam")^

agar { 1 &B 2 } [
	Benevis("na!")^
]


Sahih v1 = 0^
Sahih v2 = 14^

ta { v1 &K 10 } [
	Benevis("", v1)^
	v1 YekiBala^
]


------------------------


Grammar:

START:
	Benevis(STRING, EXPRESSION)^
	Begir(TYPE, id)^
	agar { BOOLEANEXPRESSION } [ START ]
	ta { BOOLEANEXPRESSION } [ START ]
	TYPE id = EXPRESSION^
	id SINGULAROP^


SINGULAROP:
	YekiBala
	YekiPain

TYPE:
	Sahih
	Ashari
	Harf

BOOLEANEXPRESSION:
	INDENRIFIER OPB IDENTIFIER

IDENTIFIER:
	id
	num

OPB:
	&B
	&BM
	&K
	&KM
	&MM

EXPRESSION:
	TERM E

TERM:
	FACTOR T

E:
	Jam TERM E
	Kam TERM E
	λ

T:
	Zarb FACTOR T
	Tagsim FACTOR T
	Bagimonde FACTOR T
	λ

FACTOR:
	IDENTIFIER
	( EXPRESSION )
