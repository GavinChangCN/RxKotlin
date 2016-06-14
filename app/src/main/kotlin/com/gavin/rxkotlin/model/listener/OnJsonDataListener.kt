package com.gavin.rxkotlin.model.listener;

import com.gavin.rxkotlin.model.bean.ErrorInfo

interface OnJsonDataListener {
    fun onSuccess(jsonData: String)

    fun onError(error: ErrorInfo)
}
