
//    _                               ___     ______                           _      
//   | |                             |__ \   |  ____|                         | |     
//   | |     ___  ___ ___  ___  _ __    ) |  | |__  __  ____ _ _ __ ___  _ __ | | ___ 
//   | |    / _ \/ __/ __|/ _ \| '_ \  / /   |  __| \ \/ / _` | '_ ` _ \| '_ \| |/ _ \
//   | |___|  __/\__ \__ \ (_) | | | |/ /_   | |____ >  < (_| | | | | | | |_) | |  __/
//   |______\___||___/___/\___/|_| |_|____|  |______/_/\_\__,_|_| |_| |_| .__/|_|\___|
//                                                                      | |           
//                                                                      |_|           

module lesson2

sig Node { 
    link : lone Node
}

fun parent [n: Node] : Node { 
    n.~link 
}

pred isHeader [n: Node] { 
    no parent[n]
}

pred wellformedList [n: Node] {
    all n : Node | n !in n.^link  // Checks that there are no loops in the linked list
    all n: Node { isHeader[n] or some n2 : {Node - n} | n in n2.^link } // Checks that all nodes are reachable from the header node. This is done by checking that either the node is a header node or there exists some other node n2 that has a transitive link to n
    lone n : Node | isHeader[n] // Ensures that there is exactly one header node in the linked list
}

run wellformedList
 