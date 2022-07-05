package com.damai.mycasestudy.presentation.adapter

import android.view.ViewGroup
import com.damai.core.BaseListAdapter
import com.damai.core.BaseViewHolder
import com.damai.data.diffutil.DoctorDiffUtilCallback
import com.damai.data.model.HomeDataModel

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
class DoctorListAdapter : BaseListAdapter<HomeDataModel, DoctorListAdapter.DoctorViewHolder>(
    diffUtil = DoctorDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        TODO("Not yet implemented")
    }

    inner class DoctorViewHolder(

    ) : BaseViewHolder<HomeDataModel>() {

        override fun bindData(data: HomeDataModel) {

        }
    }
}