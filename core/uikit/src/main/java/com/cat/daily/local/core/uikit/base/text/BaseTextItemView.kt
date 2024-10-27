package com.cat.daily.local.core.uikit.base.text

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.uikit.base.ext.applyMargin
import com.cat.daily.local.core.uikit.base.ext.applyPadding
import com.cat.daily.local.core.uikit.base.ext.load
import com.cat.daily.local.core.uikit.base.ext.setBackgroundView
import com.cat.daily.local.core.uikit.base.ext.setSizeValue

class BaseTextItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), RecyclerItemView<BaseTextItem.State> {

    override fun bindState(state: BaseTextItem.State) {
        load(state.value)
        setSizeValue(state.sizeValue)
        setBackgroundView(state.background)
        applyMargin(state.margins)
        applyPadding(state.padding)
    }
}