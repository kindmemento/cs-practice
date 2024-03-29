// Interval Tree is a data structure used to efficiently store and query intervals.

// 1. Concept:
// - An interval tree is a self-balancing binary search tree that stores intervals.
// - Each node in the tree represents an interval, and the subtree rooted at that node contains intervals overlapping with the node's interval.
// - It supports efficient interval-based opertaions such as searching for overlapping intervals and finding all intervals that contain a given point.

// 2. Structure:
// - Each node in the interval tree contains an interval (usually represented by its start and end points) and additional information like the maximum endpoint of intervals in its subtree.
// - The tree is constructed by recursively partitioning intervals based on their midpoint, similar to other binary search trees.

// 3. Operations:
// - Insertion: Insert a new interval into the interval tree while maintaining its properties.
// - Deletion: Remove an interval from the interval tree, adjusting the tree structure as necessary.
// - Overlap Search: Given an interval, find all intervals in the tree that overlap with it. This is done by traversing the tree and comparing intervals' start and end points.
// - Point Query: Given a point, find all intervals in the tree that contain that point. This operation is facilitated by checking if the point lies within the intervals' range stored in each node.

// Consider a calendar application where events are represented as time intervals.
// An interval tree can be employed to efficiently handle queries such as finding all events happening at a specific time or determining overlapping events.

data class Interval(val start: Int, val end: Int)

class IntervalTreeNode(
    val interval: Interval,
    val maxEnd: Int,
    var left: IntervalTreeNode? = null,
    var right: IntervalTreeNode? = null
)

class IntervalTree(intervals: List<Interval>) {
    private var root: IntervalTreeNode? = null

    init {
        intervals.forEach { insert(it) }
    }

    fun insert(interval: Interval) {
        root = insertHelper(root, interval)
    }

    private fun insertHelper(node: IntervalTreeNode?, interval: Interval): IntervalTreeNode {
        if (node == null) {
            return IntervalTreeNode(interval, interval.end)
        }

        if (interval.start < node.interval.start) {
            node.left = insertHelper(node.left, interval)
        } else {
            node.right = insertHelper(node.right, interval)
        }

        node.maxEnd = maxOf(node.maxEnd, interval.end)
        return node
    }

    fun searchOverlap(interval: Interval): List<Interval> {
        val result = mutableListOf<Interval>()
        searchOverlapHelper(root, interval, result)
        return result
    }

    private fun searchOverlapHelper(node: IntervalTreeNode?, interval: Interval, result: MutableList<Interval>) {
        if (node == null) return

        if (node.interval.start <= interval.end && node.interval.end >= interval.start) {
            result.add(node.interval)
        }

        if (node.left != null && node.left!!.maxEnd >= interval.start) {
            searchOverlapHelper(node.left, interval, result)
        }
        if (node.right != null && node.right!!.interval.start <= interval.end) {
            searchOverlapHelper(node.right, interval, result)
        }
    }
}

fun main() {
    val intervals = listOf(
            Interval(15, 20),
            Interval(10, 30),
            Interval(17, 19),
            Interval(5, 20),
            Interval(12, 15),
            Interval(30, 40)
    )

    val intervalTree = IntervalTree(intervals)

    val queryInterval = Interval(14, 16)
    val overlappingIntervals = intervalTree.searchOverlap(queryInterval)
    println("Intervals overlapping with $queryInterval: $overlappingIntervals")
}
