// The Dutch National Flag Algorithm, proposed by Edsger Dijkstra, is used to sort an array of three distinct elements (the classic version uses 0, 1, 2) in linear time and constant space.
// The idea is to maintain four regions in the array:

// 1. Red objects (e.g., 0's)
// 2. White objects (e.g., 1's)
// 3. Blue objects (e.g., 2's)
// Unknown objects

// The algorithm iterates through the array, expanding the regions as it encounters each color.

// Imagine you have balls of three different colors (red, white, and blue) and you want to sort them so that the same colors are grouped together.
// This problem is analogous to the Dutch National Flag problem, where you're given an array with three different numbers and you need to sort them in place so that the same numbers are grouped together.

fun dutchNationalFlag(arr: IntArray) {
    var low = 0
    var high = arr.size - 1
    var mid = 0

    while (mid <= high) {
        when (arr[mid]) {
            0 -> {
                // Swap low and mid elements
                arr[mid] = arr[low].also { arr[low] = arr[mid] }
                // Increment low ad mid pointers
                low++
                mid++
            }
            1 -> {
                // If it's 1, just move the mid pointer
                mid++
            }
            2 -> {
                // Swap mid and high elements
                arr[mid] = arr[high].also { arr[high] = arr[mid] }
                // Decrement high pointer
                high--
            }
        }
    }
}

fun main() {
    val arr = intArrayOf(2, 0 , 1, 2, 1, 0)
    dutchNationalFlag(arr)
    println("Sorted Array: ${arr.joinToString(", ")}")
}
