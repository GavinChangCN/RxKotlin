package com.gavin.rxkotlin.view.login

import com.gavin.rxkotlin.model.bean.UserInfo

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 15:52
 */
interface LoginView {
    fun getUserName(): String

    fun getPassword(): String

    fun clearUserName()

    fun clearPassword()

    fun showLoading()

    fun hideLoading()

    fun toMainActivity(userinfo: UserInfo)

    fun showFailedError()

}
