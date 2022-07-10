package com.damai.mycasestudy.presentation

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.damai.core.*
import com.damai.mycasestudy.R
import com.damai.mycasestudy.databinding.ActivityMainBinding
import com.damai.mycasestudy.presentation.adapter.DoctorListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : BaseActivity<MainViewModel>(), ViewDataBindingOwner<ActivityMainBinding>,
    MainView {

    private lateinit var doctorListAdapter: DoctorListAdapter
    private var searchTimer: Timer? = null

    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRvAdapter()
        addEditTextKeywordsListener()

        observeLoading()
        observeAllDoctorList()
        observeSearchedList()
    }

    /**
     * Callback from MainView.
     */
    override fun onHospitalFilterClicked() {
        viewModel.generateHospitalTextList()?.let {
            DialogUtil(
                mContext = this,
                mLifecycleOwner = this
            ).showMultipleChoicesList(
                title = getString(R.string.title_hospital_filter_dialog),
                items = it,
                initialSelectionItems = viewModel.lastHospitalSelections,
                callback = object : DialogUtil.DialogMultipleChoicesCallback {
                    override fun onSelection(
                        dialog: MaterialDialog,
                        indices: IntArray,
                        items: List<CharSequence>
                    ) {
                        viewModel.lastHospitalSelections = indices
                    }

                    override fun onAllCleared() {
                        viewModel.lastHospitalSelections = IntArray(0)
                    }
                }
            )
        }
    }

    /**
     * Callback from MainView.
     */
    override fun onSpecializationFilterClicked() {
        viewModel.generateSpecializationTextList()?.let {
            DialogUtil(
                mContext = this,
                mLifecycleOwner = this
            ).showMultipleChoicesList(
                title = getString(R.string.title_specialization_filter_dialog),
                items = it,
                initialSelectionItems = viewModel.lastSpecializationSelections,
                callback = object : DialogUtil.DialogMultipleChoicesCallback {
                    override fun onSelection(
                        dialog: MaterialDialog,
                        indices: IntArray,
                        items: List<CharSequence>
                    ) {
                        viewModel.lastSpecializationSelections = indices
                    }

                    override fun onAllCleared() {
                        viewModel.lastSpecializationSelections = IntArray(0)
                    }
                }
            )
        }
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

    private fun addEditTextKeywordsListener() {
        binding.etKeywords.afterTextChanged {
            searchTimer?.cancel()
            if (it.isNullOrBlank()) {
                viewModel.keyword = it.toString()
            } else {
                viewModel.keyword = null
            }
        }
        binding.etKeywords.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    hideSoftKeyboard(this, textView)
                    true
                }
                else -> false
            }
        }
    }
}