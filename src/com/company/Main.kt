package com.company

import com.company.sortInsert.interfaces.Check
import kotlin.Throws
import kotlin.jvm.JvmStatic
import com.company.sortInsert.SortInsert
import com.company.sortInsert.enums.OrderBy
import java.lang.Exception
import java.util.ArrayList
import kotlin.math.floor

internal class Stopwatch {
    private val start: Long = System.currentTimeMillis()

    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    fun elapsedTime(): Double {
        val now = System.currentTimeMillis()
        return (now - start) / 1000.0
    }

}

internal class MergeSort {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    fun merge(arr: ArrayList<Int>, l: Int, m: Int, r: Int) {
        // Find sizes of two subarrays to be merged
        val n1 = m - l + 1
        val n2 = r - m

        /* Create temp arrays */
        val L = IntArray(n1)
        val R = IntArray(n2)

        /*Copy data to temp arrays*/for (i in 0 until n1) L[i] = arr[l + i]
        for (j in 0 until n2) R[j] = arr[m + 1 + j]

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        var i = 0
        var j = 0

        // Initial index of merged subarry array
        var k = l
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr.removeAt(k)
                arr.add(k, L[i])
                i++
            } else {
                arr.removeAt(k)
                arr.add(k, R[j])
                j++
            }
            k++
        }

        /* Copy remaining elements of L[] if any */while (i < n1) {
            arr.removeAt(k)
            arr.add(k, L[i])
            i++
            k++
        }

        /* Copy remaining elements of R[] if any */while (j < n2) {
            arr.removeAt(k)
            arr.add(k, R[j])
            j++
            k++
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    fun sort(arr: ArrayList<Int>, l: Int, r: Int) {
        if (l < r) {
            // Find the middle point
            val m = l + (r - l) / 2

            // Sort first and second halves
            sort(arr, l, m)
            sort(arr, m + 1, r)

            // Merge the sorted halves
            merge(arr, l, m, r)
        }
    }
}

class Person(
        var name: String = "",
        var age: Int = 0
)

internal object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val min = 1
        val max = 200000
//        val randAr = intArrayOf(5, 6, 3, 2, 1, 6, 1, 3, 5, 4, 2, 6, 7, 64, 73, 54, 3, 23, 54)
//        val randAr = arrayOf("e", "f", "c", "b", "a", "f", "a", "c", "e", "d", "b", "f", "g", "64", "73", "54", "c", "23", "54")
//        val randAr = arrayOf(4.5, 6.3, 5.7, 1.23, 3.2)
        val randAr = ArrayList<Int>()

        for (i in min..max) {
            randAr.add(floor(Math.random() * (max - min + 1) + min).toInt())
        }
        println(randAr)

        // arr 1
        val arr = ArrayList<Int>()
        val ns = SortInsert<Int>()
        ns.orderBy = OrderBy.ASC
        ns.check = object : Check<Int, Any?> {
            override fun item(item: Int): Int {
                return item
            }

            override fun currentItem(currentItem: Int): Int {
                return currentItem
            }
        }

        println("starting...")
        val timer1 = Stopwatch()
        for (i in randAr) {
            ns.insert(i, arr)
        }

        val time1 = timer1.elapsedTime()
        println(arr)
        println(time1)

        // arr 2
        val timer2 = Stopwatch()
        val ob = MergeSort()

        ob.sort(randAr, 0, randAr.size - 1)

        val time2 = timer2.elapsedTime()
        println(randAr)
        println(time2)

        System.out.printf("time1 :: %f, time2 :: %f, sub :: %f\n", time1, time2, time2 - time1)
        println(randAr == arr)
    }
}