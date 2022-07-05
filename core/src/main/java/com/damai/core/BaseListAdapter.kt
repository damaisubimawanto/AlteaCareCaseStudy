package com.damai.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class BaseListAdapter<T, VH: BaseViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
): ListAdapter<T, VH>(diffUtil) {

    override fun getItemCount(): Int {
        return if (currentList.isEmpty()) {
            0
        } else {
            currentList.size
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position))
    }
}