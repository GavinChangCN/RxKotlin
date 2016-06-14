package com.gavin.rxkotlin.event

import com.gavin.rxkotlin.model.bean.UserInfo

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 17:22
 */
class LoginSuccessEvent(userInfo: UserInfo) {
    val userInfo: UserInfo = userInfo
}