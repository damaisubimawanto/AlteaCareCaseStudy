package com.damai.core

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
interface ViewDataBindingOwner<T : ViewDataBinding> {
    var originalBinding: T?

    val binding
        get() = originalBinding!!

    fun setContentViewBinding(activity: Activity, layoutResId: Int) {
        originalBinding = DataBindingUtil.setContentView(activity, layoutResId)
    }

    fun clearDataBinding() {
        originalBinding = null
    }
}