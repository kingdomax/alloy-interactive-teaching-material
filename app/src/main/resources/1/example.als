
//    _                              __    ______                           _      
//   | |                            /_ |  |  ____|                         | |     
//   | |     ___  ___ ___  ___  _ __ | |  | |__  __  ____ _ _ __ ___  _ __ | | ___ 
//   | |    / _ \/ __/ __|/ _ \| '_ \| |  |  __| \ \/ / _` | '_ ` _ \| '_ \| |/ _ \
//   | |___|  __/\__ \__ \ (_) | | | | |  | |____ >  < (_| | | | | | | |_) | |  __/
//   |______\___||___/___/\___/|_| |_|_|  |______/_/\_\__,_|_| |_| |_| .__/|_|\___|
//                                                                   | |           
//                                                                   |_|           

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
 