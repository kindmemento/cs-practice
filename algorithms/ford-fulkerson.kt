// The Ford-Fulkerson algorithm computes the maximum flow in a flow network.
// It identifies possible paths from the source to the sink while incrementally increasing the flow along these paths.
// The algorithm continues until no more augmenting paths from the source to the sink are are available.
// The "augmenting path" is a path where the flow can be increased.

// Consider a network of water pipes connecting a reservoir to a village.
// Each pipe has a capacity limit, and you want to determine the maximum amount of water that can be transported from the reservoir to the village through this network of pipes.
// This is a classic example of a Maximum Flow Problem.

fun fordFulkerson(graph: Array<IntArray>, source: Int, sink: Int): Int {
    val residualGraph = graph.map { it.clone() }.toTypedArray()
    val parent = IntArray(graph.size) { -1 }
    var maxFlow = 0

    while (bfs(residualGraph, source, sink, parent)) {
        var pathFlow = Int.MAX_VALUE
        var s = sink
        while (s != source) {
            pathFlow = minOf(pathFlow, residualGraph[parent[s]][s])
            s = parent[s]
        }

        // Update residual capacities and reverse edges
        var v = sink
        while (v != source) {
            val u = parent[v]
            residualGraph[u][v] -= pathFlow
            residualGraph[v][u] += pathFlow
            v = parent[v]
        }

        maxFlow += pathFlow
    }
    return maxFlow
}
