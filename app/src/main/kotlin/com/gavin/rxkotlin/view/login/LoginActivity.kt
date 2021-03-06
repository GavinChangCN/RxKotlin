package com.gavin.rxkotlin.view.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.gavin.rxkotlin.R
import com.gavin.rxkotlin.model.bean.UserInfo
import com.gavin.rxkotlin.presenter.normal.login.LoginPresenter
import com.gavin.rxkotlin.view.MainActivity
import com.gavin.rxkotlin.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.telephonyManager
import java.util.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity(), LoaderCallbacks<Cursor>, LoginView {


    private val mLoginPresenter: LoginPresenter = LoginPresenter(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        // Set up the login form.
        populateAutoComplete()

        metPassword.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        btnSignIn!!.setOnClickListener { attemptLogin() }
    }

    private fun populateAutoComplete() {
        var _nativePhoneNumber: String ?= telephonyManager.line1Number
        metPhone.text.clear()
        if (_nativePhoneNumber == null) {
            return
        }
        metPhone.setText(_nativePhoneNumber)

        loaderManager.initLoader(0, null, this)
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        metPhone.error = null
        metPassword.error = null

        // Store values at the time of the login attempt.
        val phone = metPhone.text.toString()
        val password = metPassword.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            metPassword.error = getString(R.string.error_invalid_password)
            focusView = metPassword
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone)) {
            metPhone.error = getString(R.string.error_field_required)
            focusView = metPhone
            cancel = true
        } else if (!isEmailValid(phone)) {
            metPhone.error = getString(R.string.error_invalid_phone)
            focusView = metPhone
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            mLoginPresenter.login()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            scrollViewLogin.visibility = if (show) View.GONE else View.VISIBLE
            scrollViewLogin.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    scrollViewLogin.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            progressBarLogin.visibility = if (show) View.VISIBLE else View.GONE
            progressBarLogin.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    progressBarLogin.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBarLogin.visibility = if (show) View.VISIBLE else View.GONE
            scrollViewLogin.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(i: Int, bundle: Bundle): Loader<Cursor> {
        return CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE),

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {

    }

    private interface ProfileQuery {
        companion object {
            val PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.IS_PRIMARY)

            val ADDRESS = 0
            val IS_PRIMARY = 1
        }
    }

    companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }

    override fun getUserName(): String = metPhone.text.toString()

    override fun getPassword(): String = metPassword.text.toString()

    override fun clearUserName() {
        metPhone.text.clear()
    }

    override fun clearPassword() {
        metPassword.text.clear()
    }

    override fun showLoading() {
        showProgress(true)
    }

    override fun hideLoading() {
        showProgress(false)
    }

    override fun toMainActivity(userinfo: UserInfo) {
        snackbar(btnSignIn, "登录成功")
        startActivity<MainActivity>()
    }

    override fun showFailedError() {
        snackbar(btnSignIn, "登录失败")
    }
}

