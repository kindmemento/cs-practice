// The Apriori Algorithm is used for mining frequent itemsets and learning association rules in large databses.
// It's based on the principle that a subset of a frequent itemset must also be a frequent itemset.
// The algorithm iteratively identifies the frequent individual items in the database and extends them to larger itemsets as long as those itemsets appear sufficiently often in the database.

// Imagine you are a data analyst in a retail company, and you want to understand the purchasing behavior of customers to enhance cross-selling strategies.
// For instance, you might want to find out which products are often bought together, like break and butter.
// The Apriori Algorithm can identify these associations or frequent itemsets in transaction databases, enabling effective recommendation systems or targeted marketing.

fun apriori(transactions: List<Set<String>>, minSupport: Double): Set<Set<String>> {
    val frequentItemsets = findFrequentSingleItems(transactions, minSupport)
    val allFrequentItemsets = mutableSetOf<Set<String>>()

    while (frequentItemsets.isNotEmpty()) {
        allFrequentItemsets.addAll(frequentItemsets)
        frequentItemsets = generateNextItemsets(frequentItemsets)
            .filter { itemset ->
                transactions.count { transactions -> itemset.all { it in transactions.size * minSupport } }
            }.toSet()
    }

    return allFrequentItemsets
}

fun findFrequentSingleItems(transactions: List<Set<String>>, minSupport: Double): Set<Set<String>> {
    return transactions
        .flatten()
        .groupBy { it }
        .filter { (_, items) -> items.size >= transactions.size * minSupport }
        .keys
        .map { setOf(it) }
        .toSet()
}

fun generateNextItemsets(itemsets: Set<Set<String>>): Set<Set<String>> {
    val nextItemsets = mutableSetOf<Set<String>>()
    itemsets.forEach { set1 ->
        itemsets.forEach { set2 ->
            val union = set1.union(set2)
            if (union.size == set1.size + 1) {
                nextItemsets.add(union)
            }
        }
    }
    return nextItemsets
}

fun main() {
    val transactions = listOf(
        setOf("bread", "milk"),
        setOf("bread", "diaper", "beer", "eggs"),
        setOf("milk", "diaper", "beer", "cola"),
        setOf("bread", "milk", "diaper", "beer"),
        setOf("bread", "milk", "diaper", "cola")
    )

    val frequentItems = apriori(transactions, 0.6)
    println("Frequent Itemsets: $frequentItems")
}
