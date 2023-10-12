// Kruskal's Algorithm is a greedy algorithm that finds the minimum spanning tree for a connected weighted graph,
// meaning it finds a subset of the edges that forms a tree that includes every vertex, where the total weight of all the edges in the tree is minimized.

// Imagine a group of islands, and you're tasked with building bridges to connect all of them.
// Bridges have different costs based on the distance and difficulty of construction.
// Using Kruskal's Algorithm, you can find the most cost-effective way to connect all the islands without forming any cycles (loops).

data class Edge(val u: Int, val v: Int, val weight: Int)

fun find(parent: IntArray, i: Int): Int {
    if (parent[i] != i) {
        parent[i] = find(parent, parent[i])
    }
    return parent[i]
}

fun union(parent: IntArray, x: Int, y: Int) {
    val xRoot = find(parent, x)
    val yRoot = find(parent, y)
    parent[xRoot] = yRoot
}
