package com.damai.core

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsSingleChoice

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
class DialogUtil(
    private val mContext: Context,
    private val mLifecycleOwner: LifecycleOwner
) {

    fun showSingleChoiceList(
        title: String?,
        items: List<String>,
        initialSelection: Int = -1,
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

    interface DialogSingleChoiceCallback {
        fun onSelection(dialog: MaterialDialog, which: Int, text: CharSequence?)
    }
}