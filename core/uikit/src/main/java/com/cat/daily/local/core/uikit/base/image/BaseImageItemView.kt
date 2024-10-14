package com.cat.daily.local.core.uikit.base.image

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.uikit.base.ext.load
import com.cat.daily.local.core.uikit.base.ext.setSizeValue

class BaseImageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), RecyclerItemView<BaseImageItem.State> {

    override fun bindState(state: BaseImageItem.State) {
        load(state.value)
        setSizeValue(state.sizeValue)
    }
}