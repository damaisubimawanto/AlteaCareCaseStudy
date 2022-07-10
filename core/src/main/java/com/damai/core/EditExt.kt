package com.damai.core

import android.widget.EditText

/**
 * Created by damai.subimawanto on 7/10/2022.
 */

fun EditText.onTextChanged(listener: (p0: CharSequence?) -> Unit) {
    addTextChangedListener(TextWatcherFactory().onChanged(listener))
}

fun EditText.afterTextChanged(listener: (p0: CharSequence?) -> Unit) {
    addTextChangedListener(TextWatcherFactory().afterTextChanged(listener))
}

fun EditText.beforeTextChanged(listener: (p0: CharSequence?) -> Unit) {
    addTextChangedListener(TextWatcherFactory().beforeTextChanged(listener))
}