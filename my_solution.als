/* Syntax example from lesson 1 */
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

/* Syntax example from lesson 2 */
//module lesson2
//
//sig Node { 
//    link : lone Node
//}
//
//fun parent [n: Node] : Node { 
//    n.~link 
//}
//
//pred isHeader [n: Node] { 
//    no parent[n]
//}
//
//pred wellformedList [n: Node] {
//    all n : Node | n !in n.^link  // Checks that there are no loops in the linked list
//    all n: Node { isHeader[n] or some n2 : {Node - n} | n in n2.^link } // Checks that all nodes are reachable from the header node. This is done by checking that either the node is a header node or there exists some other node n2 that has a transitive link to n
//    lone n : Node | isHeader[n] // Ensures that there is exactly one header node in the linked list
//}
//
//run wellformedList
 