package com.gavin.rxkotlin.model.biz.impl

import com.gavin.rxkotlin.model.bean.UserInfo
import com.gavin.rxkotlin.model.listener.OnDataListener

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 15:16
 */
public interface UserModel {
    fun login(phone: String, password: String, loginListener: OnDataListener<UserInfo>);
}