package com.damai.mycasestudy.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.damai.core.BaseListAdapter
import com.damai.core.BaseViewHolder
import com.damai.data.diffutil.DoctorDiffUtilCallback
import com.damai.data.model.HomeDataModel
import com.damai.mycasestudy.databinding.ItemRecyclerDoctorBinding

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class DoctorListAdapter : BaseListAdapter<HomeDataModel, DoctorListAdapter.DoctorViewHolder>(
    diffUtil = DoctorDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = ItemRecyclerDoctorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DoctorViewHolder(binding = view)
    }

    inner class DoctorViewHolder(
        private val binding: ItemRecyclerDoctorBinding
    ) : BaseViewHolder<HomeDataModel>(binding = binding) {

        override fun bindData(data: HomeDataModel) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}