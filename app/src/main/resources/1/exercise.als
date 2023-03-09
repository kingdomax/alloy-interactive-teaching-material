
//    _                              __    ______                   _          
//   | |                            /_ |  |  ____|                 (_)         
//   | |     ___  ___ ___  ___  _ __ | |  | |__  __  _____ _ __ ___ _ ___  ___ 
//   | |    / _ \/ __/ __|/ _ \| '_ \| |  |  __| \ \/ / _ \ '__/ __| / __|/ _ \
//   | |___|  __/\__ \__ \ (_) | | | | |  | |____ >  <  __/ | | (__| \__ \  __/
//   |______\___||___/___/\___/|_| |_|_|  |______/_/\_\___|_|  \___|_|___/\___|
//                                                                             
//                                                                             

/*
 * In this exercise, you will modify a small model about Russell's famous variation on that problem
 * -- the barber paradox: In a village in which the barber shaves every man who doesn't shave himself, who shaves the barber?
 * (i.e. You only need to modify the signature part of the model and run it against our validator. Solve one question at a time)
 *
 * (a) Use the analyzer to show that the model
 * is indeed inconsistent, at least for villages of small sizes.
 *
 * (b) Some feminists have noted that the paradox
 * disappears if the existence of women is acknowledged.
 * Make a new version of the model that classifies villagers
 * into men (who need to be shaved)
 * and women (who don't), and show that there is now a solution.
 *
 * (c) A more drastic solution, noted by Edsger Dijkstra,
 * is to allow the possibility of there being no barber.
 * Modify the original model accordingly, and show that there is now a solution.
 *
 * (d) Finally, make a variant of the original model that
 * allows for multiple barbers. Show that there is again a solution.
 *
 * Referrence: Exercise A.3.3 on page 243 of Software Abstractions, by Daniel Jackson
 */

sig Man {shaves: set Man}
one sig Barber extends Man {}

fact {
   Barber.shaves = {m: Man | m not in m.shaves}
}

run { }
 