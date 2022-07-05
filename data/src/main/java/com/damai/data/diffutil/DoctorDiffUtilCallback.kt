package com.damai.data.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.damai.data.model.HomeDataModel

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class DoctorDiffUtilCallback : DiffUtil.ItemCallback<HomeDataModel>() {

    override fun areItemsTheSame(oldItem: HomeDataModel, newItem: HomeDataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeDataModel, newItem: HomeDataModel): Boolean {
        return oldItem == newItem
    }
}