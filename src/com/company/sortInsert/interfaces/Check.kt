package com.company.sortInsert.interfaces

interface Check<T, I> {
    fun item(item: T): I
    fun currentItem(currentItem: T): I
}