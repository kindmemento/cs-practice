// Floyd's Cycle Detection Algorithm is an algorithm used to detect cycles in a sequence, such as a linked list.
// It uses two pointers, usually called the "tortoise" and the "hare", which move at different speeds.
// The hare moves twice as fast as the tortoise.
// If there is a cycle, the hare will eventually meet the tortoise again somewhere inside the cycle.

// Imagine you're creating a fitness tracking application that includes a feature for detecting repetitive patterns or loops in a user's running route.
// Suppose the GPS data forms a linked list where each point is a node; you want to determine if the runner is moving in a loop (circular route) or not.
// Floyd's Cycle Detection Algorithm can efficiently identify if the linked list of GPS data points has a cycle.

class ListNode(val value: Int) {
    var next: ListNode? = null
}

fun hasCycle(head: ListNode?): Boolean {
    var tortoise = head
    var hare = head

    while (hare != null && hare.next != null) {
        tortoise = tortoise?.next
        hare = hare.next?.next

        if (tortoise == hare) {
            return true
        }
    }

    return false
}

fun main() {
    val head = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
                next = ListNode(4).apply {
                    next = ListNode(5).apply {
                        next = next.next // Creates a cycle
                    }
                }
            }
        }
    }

    println("Does the linked list have a cycle ${hasCycle(head)}")
}
