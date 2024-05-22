package com.damai.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    abstract val layoutResourceId: Int
    abstract val viewModel: VM

    private var _binding: VB? = null
    val binding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this
        if (this is BaseView) {
            binding.setVariable(BR.view, this)
        }
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun checkIfActivityFinished(): Boolean {
        return isFinishing || isDestroyed
    }
}