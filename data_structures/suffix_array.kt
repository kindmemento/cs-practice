// A Suffix Array is a sorted array of all the suffixes of a given string.
// It provides an efficient way to perform operations such as string matching, finding the longest common prefix, and other related problems.

// Suppose you're building a text editor with a feature to find all occurrences of a substring within a larger document.
// A naive approach would involve checking every position in the document, which could be inefficient for large documents.
// A Suffix Array can accelerate this operation by quickly identifying potential match positions.

fun buildSuffixArray(s: String): IntArray {
    val n = s.length
    val suffixes = Array(n) { i -> s.substring(i) to i }
    suffixes.sortBy { it.first }
    return suffixes.map { it.second }.tointArray()
}

