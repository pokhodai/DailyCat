package com.cat.daily.local.core.uikit.base.image

import android.content.Context
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.base.value.ImageValue
import com.cat.daily.local.core.uikit.base.value.SizeValue

class BaseImageItem {

    data class State(
        val id: String,
        val value: ImageValue,
        val sizeValue: SizeValue,
    ) : RecyclerState {
        override val provideId: String = id
        override val viewType: Int = BaseImageItem::class.java.hashCode()
        override fun getView(context: Context) = BaseImageItemView(context)
    }
}