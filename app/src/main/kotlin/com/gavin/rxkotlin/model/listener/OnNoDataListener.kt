package com.gavin.rxkotlin.model.listener;

import com.gavin.rxkotlin.model.bean.ErrorInfo;

interface OnNoDataListener {
    fun onSuccess()

    fun onError(error: ErrorInfo)
}
