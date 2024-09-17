package com.cat.school.core.common

interface ICache<T> {
    operator fun set(key: String, data: T?)

    operator fun get(key: String): T?

    fun remove(key: String)

    fun clear()
}
