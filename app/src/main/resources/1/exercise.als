
//     _____ _                   _                      ______                   _          
//    / ____(_)                 | |                    |  ____|                 (_)         
//   | (___  _  __ _ _ __   __ _| |_ _   _ _ __ ___    | |__  __  _____ _ __ ___ _ ___  ___ 
//    \___ \| |/ _` | '_ \ / _` | __| | | | '__/ _ \   |  __| \ \/ / _ \ '__/ __| / __|/ _ \
//    ____) | | (_| | | | | (_| | |_| |_| | | |  __/   | |____ >  <  __/ | | (__| \__ \  __/
//   |_____/|_|\__, |_| |_|\__,_|\__|\__,_|_|  \___|   |______/_/\_\___|_|  \___|_|___/\___|
//              __/ |                                                                       
//             |___/                                                                                                         

/*
 * Consider the set of all sets that do not contain
 * themselves as members. Does it contain itself?
 *
 * In this exercise, you will modify a small model
 * about Russell's famous variation on that problem
 * -- the barber paradox: In a village in which the
 * barber shaves every man who doesn't shave
 * himself, who shaves the barber?
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
 