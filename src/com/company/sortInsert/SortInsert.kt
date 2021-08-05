package com.company.sortInsert

import com.company.sortInsert.enums.OrderBy
import com.company.sortInsert.interfaces.Check
import kotlin.math.roundToInt

class SortInsert<T> {
    var orderBy: OrderBy = OrderBy.ASC
    var check: Check<T, Any?> = object : Check<T, Any?> {
        override fun item(item: T): Any? {
            return item
        }

        override fun currentItem(currentItem: T): Any? {
            return currentItem
        }
    }

    private fun compare(arr: ArrayList<T>, index: Int, i: T): Int {
        return compareValues(check.item(i) as Comparable<*>, check.currentItem(arr[index]) as Comparable<*>)
    }

    fun insert(i: T, arr: ArrayList<T>) {
        var start = 0
        val end = arr.size - 1
        var mid = start + ((end - start).toFloat() / 2).roundToInt()

        while (true) {
            if (start == mid) {
                val index: Int = if (orderBy == OrderBy.ASC) {
                    if (end < 0 || compare(arr, mid, i) < 0) start else start + 1
                } else {
                    if (end < 0 || compare(arr, mid, i) > 0) start else start + 1
                }
                arr.add(index, i)
                break
            }
            val a = compare(arr, mid - 1, i)
            val c = if (orderBy == OrderBy.ASC) a < 0 else a > 0
            start = if (c) start else mid
            mid = if (c) start + ((mid - start).toFloat() / 2).roundToInt() - 1 else if (a != 0) start + ((end - start).toFloat() / 2).roundToInt() else mid
        }
    }
}