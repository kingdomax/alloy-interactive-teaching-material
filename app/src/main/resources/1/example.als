
//     _____ _                   _                      _____             _              
//    / ____(_)                 | |                    / ____|           | |             
//   | (___  _  __ _ _ __   __ _| |_ _   _ _ __ ___   | (___  _   _ _ __ | |_ __ ___  __ 
//    \___ \| |/ _` | '_ \ / _` | __| | | | '__/ _ \   \___ \| | | | '_ \| __/ _` \ \/ / 
//    ____) | | (_| | | | | (_| | |_| |_| | | |  __/   ____) | |_| | | | | || (_| |>  <  
//   |_____/|_|\__, |_| |_|\__,_|\__|\__,_|_|  \___|  |_____/ \__, |_| |_|\__\__,_/_/\_\ 
//              __/ |                                           __/ |                     
//             |___/                                           |___/                      

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
 