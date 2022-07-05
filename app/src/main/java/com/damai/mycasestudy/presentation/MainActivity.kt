package com.damai.mycasestudy.presentation

import android.os.Bundle
import com.damai.core.BaseActivity
import com.damai.core.ViewDataBindingOwner
import com.damai.mycasestudy.R
import com.damai.mycasestudy.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>(), ViewDataBindingOwner<ActivityMainBinding> {

    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}