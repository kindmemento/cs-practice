// Selection Sort is a simple comparison-based sorting algorithm.
// It divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list,
// and a sublist of the remaining unsorted items that occupy the rest of the list.
// The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist,
// exchanging (swapping) it with the leftmost unsorted element, and moving the sublist boundaries one element to the right.

// Imaigne you're at a fruit market trying to arrange a row of fruit baskets in order of increasing weight.
// You start by finding the lightest basket and swapping it with the basket in the first position.
// Then you look for the next lighest among the remaining baskets, swapping it with the basked in the second position, and so on.
// This process is akin to how Selection Sort arranges data.

fun selectionSort(arr: IntArray) {
    val n = arr.size

    for (i in 0 until n - 1) {
        var minIdx = i

        for (j in i + 1 until n) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j
            }
        }

        // Swap the found minimum element with the first element
        val temp = arr[minIdx]
        arr[minIdx] = arr[i]
        arr[i] = temp
    }
}

fun main() {
    val numbers = intArrayOf(64, 25, 12, 22, 11)
    selectionSort(numbers)
    println("Sorted array: ${numbers.joinToString(", ")}")
}
