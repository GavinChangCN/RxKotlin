package com.gavin.rxkotlin.model.biz.impl

import com.gavin.rxkotlin.model.BaseApi
import com.gavin.rxkotlin.model.bean.UserInfo
import com.gavin.rxkotlin.model.listener.OnDataListener
import com.zhy.http.okhttp.OkHttpUtils
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 15:18
 */
class UserModelImpl : UserModel, BaseApi() {

    override fun login(phone: String, password: String, loginListener: OnDataListener<UserInfo>) {
        val _params = HashMap<String, String>()
        _params.put("mobile", phone)
        _params.put("password", password)
        _params.put("device_id", "test_id")
        _params.put("version", "1.1.0")
        _params.put("device_token", "test_token")
        _params.put("platform", "Android")
        sLoginParams = _params //for auto login
        OkHttpUtils.post()
                .url(URL_BASE + "api/user/login")
                .params(_params)
                .build()
                .execute(createCallback(loginListener, UserInfo::class.java))
    }

}
