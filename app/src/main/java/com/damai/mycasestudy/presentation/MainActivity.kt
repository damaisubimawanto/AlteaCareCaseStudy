package com.damai.mycasestudy.presentation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.damai.core.BaseActivity
import com.damai.core.ViewDataBindingOwner
import com.damai.core.gone
import com.damai.core.visible
import com.damai.mycasestudy.R
import com.damai.mycasestudy.databinding.ActivityMainBinding
import com.damai.mycasestudy.presentation.adapter.DoctorListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>(), ViewDataBindingOwner<ActivityMainBinding>,
    MainView {

    private lateinit var doctorListAdapter: DoctorListAdapter

    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRvAdapter()

        observeLoading()
        observeAllDoctorList()
        observeSearchedList()
    }

    private fun setRvAdapter() {
        binding.rvDoctors.apply {
            doctorListAdapter = DoctorListAdapter()
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = doctorListAdapter
        }
    }

    private fun observeLoading() {
        observeData(viewModel.loading) {
            when (it) {
                true -> {
                    binding.progressCircular.visible()
                    binding.groupMain.gone()
                }
                else -> {
                    binding.progressCircular.gone()
                    binding.groupMain.visible()
                }
            }
        }
    }

    private fun observeAllDoctorList() {
        observeData(viewModel.doctorListResponse) { result ->
            result?.let { dataList ->
                doctorListAdapter.submitList(dataList)
            }
        }
    }

    private fun observeSearchedList() {
        observeData(viewModel.searchedListResponse) { result ->
            result?.let { dataList ->
                doctorListAdapter.submitList(dataList)
            }
        }
    }
}