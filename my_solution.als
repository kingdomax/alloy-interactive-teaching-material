/* Syntax example of Signature & Multiplicities & Relations */
module lesson1

abstract sig Person {
    father: lone Man,   // fathers are men and everyone has at most one
    mother: lone Woman  // mothers are women and everyone has at most one
}

/* men are persons */
sig Man extends Person {
    wife: lone Woman    // wives are women and every man has at most one
}

/* women are persons */
sig Woman extends Person {
    husband: lone Man   // husbands are men and every woman has at most one
}
run {}

/* No instances */
//sig List { header: lone Node } 
//sig Node { link: lone Node }
//run {no List and no Node} for 3
