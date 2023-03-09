
//    _                               ___     ______                   _          
//   | |                             |__ \   |  ____|                 (_)         
//   | |     ___  ___ ___  ___  _ __    ) |  | |__  __  _____ _ __ ___ _ ___  ___ 
//   | |    / _ \/ __/ __|/ _ \| '_ \  / /   |  __| \ \/ / _ \ '__/ __| / __|/ _ \
//   | |___|  __/\__ \__ \ (_) | | | |/ /_   | |____ >  <  __/ | | (__| \__ \  __/
//   |______\___||___/___/\___/|_| |_|____|  |______/_/\_\___|_|  \___|_|___/\___|

/*
 * An Alloy model of the song "I Am My Own Grandpa" by Dwight B. Latham and Moe Jaffe.
 * The full song lyrics, which describe an isomorophic solution, are included at the end of this file.
 *
 * This exercise is to produce a man who is his own grandfather without resorting to incest or time travel.
 * Executing the predicate "ownGrandpa" will demonstrate how such a thing can occur.
 *
 * Referrence: challenge is created by Daniel Jackson
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
  // Enforces the convention that a person cannot be both a wife and a mother
  // ...
  // Enforces the convention that a person cannot be both a husband and a father
  // ...
}

fun grandpas [p: Person] : set Person {
  // Takes a person p as input and returns the set of all grandfathers of p
  // ...
}

pred ownGrandpa [m: Man] { m in grandpas[m] }

run ownGrandpa for 4 Person // Command generates an instance of the model with 4 persons and checks whether any of the men in the instance are their own grandfathers

/*
I Am My Own Grandpa by Dwight B. Latham and Moe Jaffe

Many many years ago, when I was twenty-three,
I was married to a widow as pretty as can be,
This widow had a grown-up daughter who had hair of red,
My father fell in love with her and soon the two were wed.

  I'm my own grandpa, I'm my own grandpa.
  It sounds funny, I know, but it really is soÑ
  I'm my own grandpa.

This made my dad my son-in-law and changed my very life,
For my daughter was my mother, for she was my father's wife.
To complicate the matter, even though it brought me joy,
I soon became the father of a bouncing baby boy.

My little baby thus became a brother-in-law to dad,
And so became my uncle, though it made me very sad,
For if he was my uncle then that also made him brother
To the widow's grown-up daughter, who of course was my step-mother.

Father's wife then had a son who kept them on the run.
And he became my grandchild for he was my daughter's son.
My wife is now my mother's mother and it makes me blue,
Because although she is my wife, she's my grandmother, too.

Oh, if my wife's my grandmother then I am her grandchild.
And every time I think of it, it nearly drives me wild.
For now I have become the strangest case you ever sawÑ
As the husband of my grandmother, I am my own grandpa.

  I'm my own grandpa, I'm my own grandpa.
  It sounds funny, I know, but it really is soÑ
  I'm my own grandpa.
  I'm my own grandpa, I'm my own grandpa.
  It sounds funny, I know, but it really is soÑ
  I'm my own grandpa.
*/
 