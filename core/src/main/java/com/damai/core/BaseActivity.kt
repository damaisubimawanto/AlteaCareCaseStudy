package com.damai.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {

    abstract val layoutResourceId: Int
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutIfDefined()
    }

    private fun setLayoutIfDefined() {
        if (this is ViewDataBindingOwner<*>) {
            setContentViewBinding(
                activity = this,
                layoutResId = layoutResourceId
            )
            binding.setVariable(BR.vm, viewModel)
            binding.lifecycleOwner = this
            if (this is BaseView) {
                binding.setVariable(BR.view, this)
            }
        } else {
            setContentView(layoutResourceId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is ViewDataBindingOwner<*>) {
            clearDataBinding()
        }
    }

    fun checkIfActivityFinished(): Boolean {
        return isFinishing || isDestroyed
    }
}