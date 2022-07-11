package com.damai.mycasestudy.presentation

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
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

    private val mSearchTimerTask: TimerTask
        get() = object : TimerTask() {
            override fun run() {
                if (checkIfActivityFinished()) return
                runOnUiThread {
                    viewModel.doSearch()
                }
            }
        }

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

    override fun onDestroy() {
        searchTimer?.cancel()
        searchTimer = null
        super.onDestroy()
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
                        viewModel.doSearch()
                    }

                    override fun onAllCleared() {
                        viewModel.lastHospitalSelections = IntArray(0)
                        viewModel.doSearch()
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
                        viewModel.doSearch()
                    }

                    override fun onAllCleared() {
                        viewModel.lastSpecializationSelections = IntArray(0)
                        viewModel.doSearch()
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

            val decoration = DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
            ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.line_divider
            )?.let {
                decoration.setDrawable(it)
            }
            addItemDecoration(decoration)
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
                viewModel.keyword = null
                viewModel.doSearch()
            } else {
                viewModel.keyword = it.toString()

                /* Hit to Search API */
                searchTimer = Timer().apply {
                    schedule(mSearchTimerTask, 1_000L)
                }
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