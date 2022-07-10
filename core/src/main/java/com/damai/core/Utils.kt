package com.damai.core

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by damai.subimawanto on 7/10/2022.
 */

fun hideSoftKeyboard(activity: Activity, mainView: View?) {
    if (mainView == null) return
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(mainView.windowToken, 0)
}

/**
 * @see https://gist.github.com/BrandonJF/3578fa29eabed474a53f75ac30f8dbee
 **/
class TextWatcherFactory {

    fun onChanged(onTextChanged: (p0: CharSequence?) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = onTextChanged(p0)
        }
    }


    fun beforeTextChanged(beforeTextChanged: (p0: CharSequence?) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = beforeTextChanged(p0)

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = beforeTextChanged(p0)

        }
    }

    fun afterTextChanged(afterTextChanged: (p0: CharSequence?) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = afterTextChanged(p0)

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }
    }
}