package com.damai.core

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class BaseViewHolder<T>(
    binding: ViewDataBinding? = null,
    view: View? = null
) : RecyclerView.ViewHolder(
    binding?.root ?: view ?: throw IllegalStateException("Please use either binding or view")
) {
    abstract fun bindData(data: T)
}