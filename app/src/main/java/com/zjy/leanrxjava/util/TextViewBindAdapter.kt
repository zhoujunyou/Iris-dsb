package com.zjy.leanrxjava.util

import android.text.Spanned
import android.widget.TextView

/**

 *Description: 代码来自TextViewBindingAdapter 避免text和textview循环绑定
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/8

 */
class TextViewBindAdapter {
    companion object {
        fun setText(view: TextView, text: CharSequence?) {
            val oldText = view.text
            if (text === oldText || text == null && oldText.length == 0) {
                return
            }
            if (text is Spanned) {
                if (text == oldText) {
                    return  // No change in the spans, so don't set anything.
                }
            } else if (!haveContentsChanged(text, oldText)) {
                return  // No content changes, so don't set anything.
            }
            view.text = text
        }

        private fun haveContentsChanged(str1: CharSequence?, str2: CharSequence?): Boolean {
            if (str1 == null != (str2 == null)) {
                return true
            } else if (str1 == null) {
                return false
            }
            val length = str1.length
            if (length != str2!!.length) {
                return true
            }
            for (i in 0 until length) {
                if (str1[i] != str2[i]) {
                    return true
                }
            }
            return false
        }
    }

}