package com.cat.school.core.common.base

import com.cat.school.core.common.ICache
import java.util.concurrent.ConcurrentHashMap

abstract class MemoryCache<T> : ICache<T> {

    private val dataMap = ConcurrentHashMap<String, T?>()

    override operator fun set(key: String, data: T?) {
        dataMap[key] = data
    }

    override operator fun get(key: String): T? {
        return dataMap[key]
    }

    override fun clear() {
        dataMap.clear()
    }

    override fun remove(key: String) {
        dataMap.remove(key)
    }
}