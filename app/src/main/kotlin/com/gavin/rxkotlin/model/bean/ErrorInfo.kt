package com.gavin.rxkotlin.model.bean

import com.google.gson.annotations.SerializedName

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 15:31
 */
class ErrorInfo {
    @SerializedName("errorcode")
    var errorCode: String? = null
    @SerializedName("errormsg")
    var errorMsg: String? = null
}