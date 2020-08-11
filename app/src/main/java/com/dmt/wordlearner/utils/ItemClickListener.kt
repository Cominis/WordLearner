package com.dmt.wordlearner.utils

class ItemClickListener<T>(private val click: (item: T) -> Unit) {
    fun onClick(item: T) = click(item)
}