
/*
 * Solutions to lesson2 exercise
 */

abstract sig Person {
  father: lone Man,
  mother: lone Woman
}

sig Man extends Person { wife: lone Woman }

sig Woman extends Person { husband: lone Man }

fact Biology { no p: Person | p in p.^(mother+father) } // Ensures that there are no circular references in the family tree

fact Terminology { wife = ~husband } // Specifies that the wife of a man is the same as the husband of his wife

fact SocialConvention {
  no wife & *(mother+father).mother // Enforces the convention that a person cannot be both a wife and a mother
  no husband & *(mother+father).father // Enforces the convention that a person cannot be both a husband and a father
}

fun grandpas [p: Person] : set Person {
  let parent = mother + father + father.wife + mother.husband | p.parent.parent & Man // Takes a person p as input and returns the set of all grandfathers of p
}

pred ownGrandpa [m: Man] { m in grandpas[m] }

run ownGrandpa for 4 Person // Command generates an instance of the model with 4 persons and checks whether any of the men in the instance are their own grandfathers
 