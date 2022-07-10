package com.damai.core

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.afollestad.materialdialogs.list.listItemsSingleChoice

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class DialogUtil(
    private val mContext: Context,
    private val mLifecycleOwner: LifecycleOwner
) {

    @SuppressLint("CheckResult")
    fun showSingleChoiceList(
        title: String?,
        items: List<String>,
        initialSelection: Int,
        callback: DialogSingleChoiceCallback?
    ) {
        MaterialDialog(mContext).show {
            if (!title.isNullOrBlank()) {
                title(text = title)
            }
            positiveButton(R.string.filter)
            lifecycleOwner(mLifecycleOwner)
            listItemsSingleChoice(
                items = items,
                initialSelection = initialSelection
            ) { dialog, index, text ->
                callback?.onSelection(dialog, index, text)
            }
        }
    }

    @SuppressLint("CheckResult")
    fun showMultipleChoicesList(
        title: String?,
        items: List<String>,
        initialSelectionItems: IntArray,
        callback: DialogMultipleChoicesCallback?
    ) {
        MaterialDialog(mContext).show {
            if (!title.isNullOrBlank()) {
                title(text = title)
            }
            positiveButton(R.string.filter)
            negativeButton(R.string.clear_all) {
                callback?.onAllCleared()
            }
            lifecycleOwner(mLifecycleOwner)
            listItemsMultiChoice(
                items = items,
                initialSelection = initialSelectionItems
            ) { dialog, indices, items ->
                callback?.onSelection(dialog, indices, items)
            }
        }
    }

    interface DialogSingleChoiceCallback {
        fun onSelection(dialog: MaterialDialog, which: Int, text: CharSequence?)
    }

    interface DialogMultipleChoicesCallback {
        fun onSelection(dialog: MaterialDialog, indices: IntArray, items: List<CharSequence>)
        fun onAllCleared()
    }
}