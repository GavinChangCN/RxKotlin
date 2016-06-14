package com.gavin.rxkotlin.presenter.normal.login

import android.os.Handler
import com.gavin.rxkotlin.model.bean.ErrorInfo
import com.gavin.rxkotlin.model.bean.UserInfo
import com.gavin.rxkotlin.model.biz.impl.UserModel
import com.gavin.rxkotlin.model.biz.impl.UserModelImpl
import com.gavin.rxkotlin.model.listener.OnDataListener
import com.gavin.rxkotlin.view.login.LoginView

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 15:51
 */
class LoginPresenter(loginView: LoginView) {
    private var userModel: UserModel = UserModelImpl()
    private var loginView: LoginView = loginView;
    private val mHandler: Handler = Handler();

    open fun login() {
        loginView.showLoading();
        userModel.login(loginView.getUserName(), loginView.getPassword(), LoginListener());
    }

    open fun clear() {
        loginView.clearUserName();
        loginView.clearPassword();
    }

    inner class LoginListener : OnDataListener<UserInfo> {
        override fun onSuccess(userinfo: UserInfo) {
            mHandler.post(Runnable() {
                run {
                    loginView.toMainActivity(userinfo);
                    loginView.hideLoading();
                }
            })
        }

        override fun onError(error: ErrorInfo) {
            //需要在UI线程执行
            mHandler.post(Runnable {
                run {
                    loginView.showFailedError()
                    loginView.hideLoading()
                }
            })
        }

    }

}