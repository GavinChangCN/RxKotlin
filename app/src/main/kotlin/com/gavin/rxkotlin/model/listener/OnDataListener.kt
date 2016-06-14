package com.gavin.rxkotlin.model.listener

import com.gavin.rxkotlin.model.bean.ErrorInfo

interface OnDataListener<T> {
    fun onSuccess(t: T)

    fun onError(error: ErrorInfo)
}
