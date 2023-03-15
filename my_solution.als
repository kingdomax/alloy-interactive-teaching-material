/* Instance founded scenario */
abstract sig Person {
    father: lone Man,
    mother: lone Woman 
}
sig Man extends Person { wife: lone Woman}
sig Woman extends Person { husband: lone Man }
run {}


/* No instance scenario */
//sig Man {shaves: set Man}
//one sig Barber extends Man {}
//fact { Barber.shaves = {m: Man | m not in m.shaves} }
//run { }


/* Syntax error scenario */
//sig List { header: lone Node } 
//sig Node { link: lone Node }
//run {no Lis and no Nod} for 3