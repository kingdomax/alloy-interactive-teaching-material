
//    _                               ____     ______                           _      
//   | |                             |___ \   |  ____|                         | |     
//   | |     ___  ___ ___  ___  _ __   __) |  | |__  __  ____ _ _ __ ___  _ __ | | ___ 
//   | |    / _ \/ __/ __|/ _ \| '_ \ |__ <   |  __| \ \/ / _` | '_ ` _ \| '_ \| |/ _ \
//   | |___|  __/\__ \__ \ (_) | | | |___) |  | |____ >  < (_| | | | | | | |_) | |  __/
//   |______\___||___/___/\___/|_| |_|____/   |______/_/\_\__,_|_| |_| |_| .__/|_|\___|
//                                                                       | |           
//                                                                       |_|           

/* 
 * An alloy model, Dreadbury Mansion Mystery, illustrates several uses of FOL operators.
 *
 * Dreadbury Mansion Mystery
 * - Someone who lived in Dreadbury Mansion killed Aunt Agatha.
 * - Agatha, the Butler and Charles were the only people who lived in Dreadbury Mansion.
 * - A killer always hates their victim, and is never richer than their victim.
 * - Charles hates no one that aunt Agatha hates.
 * - Agatha hates everyone except the butler.
 * - The butler hates everyone not richer than Aunt Agatha.
 * - The butler also hates everyone Agatha hates.
 * - No one hates everyone.
 * - Agatha is not the butler.
 * - Who killed Aunt Agatha?
 *
 * Referrence: example is part of Formal Methods for Software Engineering course. Prof. Dr. Jan Oliver Ringert, Bauhaus-Universitat Weimar
 */

module lesson3

abstract sig Person {
  killed: set Person,
  hates: set Person,
  richer: set Person
}

one sig Agatha, Butler, Charles extends Person {}

pred nativeEncoding() {
  some killed.Agatha
  killed in hates and no (killed & richer)
  no (Charles.hates & Agatha.hates)
  (Person - Butler) in Agatha.hates
  (Person - richer.Agatha) in Butler.hates
  Agatha.hates in Butler.hates
  all x : Person | Person != x.hates
}

run nativeEncoding for 3
 