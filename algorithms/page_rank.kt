// The PageRank algorithm is a way of measuring the importance of website pages.
// Developed by Larry Page and Sergey Brin while at Stanford University, it assigns a numerical weighting to each element of a hyperlinked set of documents,
// such as the world Wide Web, with the purpose of measuring its relative importance within the set.

// Imagine you're creating a search engine, and you need to rank websites based on their importance and relevance.
// The PageRank algorithm can help with this.
// It ranks each website based on the number and quality of links to it, under the assumption that more important websites are likely to receive more links from other websites.

fun pageRank(links: Map<Int, List<Int>>, numIterations: Int = 100, dampingFactor: Double = 0.85): Map<Int, Double> {
    val numPages = links.size
    var ranks = links.keys.associateWith { 1.0 / numPages }

    for (i in 0 until numIterations) {
        val newRanks = mutableMapOf<Int, Double>()
        links.forEach { (page, linkedPages) ->
            val rankSum = linkedPages.sumOf { ranks[it] ?: 0.0 } / linkedPages.size
            newRanks[page] = (1 - dampingFactor) / numPages + dampingFactor * rankSum
        }
        ranks = newRanks
    }
    return ranks
}

fun main() {
    val links = mapOf(
        1 to listOf(2, 3),
        2 to listOf(3),
        3 to listOf(1),
        4 to listOf(2, 3),
    )
    val pageRanks = pageRank(links)
    println("Page Ranks: $pageRanks")
}
