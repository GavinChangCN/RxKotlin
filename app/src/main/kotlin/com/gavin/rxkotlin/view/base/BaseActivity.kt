package com.gavin.rxkotlin.view.base

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.gavin.rxkotlin.R
import com.gavin.rxkotlin.app.GavinApplication
import com.gavin.swipeback.app.SwipeBackActivity


/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-21
 * Time: 17:15
 */
open class BaseActivity: SwipeBackActivity() {

    protected open val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GavinApplication.addActivity(this)
        println("$TAG onCreate()")
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("$TAG onDestroy()")
        GavinApplication.removeActivity(this)
    }

    open fun setViewListener() {

    }

    open fun enterIntoActivity(tag: String) {
    }

    /**
     * normal toast
     */
    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        when(id) {
            android.R.id.home -> finish()
            R.id.menu_settings -> { println("点击了设置"); toast("点击了设置") }
            R.id.menu_about -> { println("点击了关于"); toast("点击了关于") }
            R.id.menu_quit -> GavinApplication.exit()
        }

        return super.onOptionsItemSelected(item)
    }

}