package com.gavin.rxkotlin.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.gavin.rxkotlin.R
import com.gavin.rxkotlin.event.LoginSuccessEvent
import com.gavin.rxkotlin.view.base.BaseActivity
import com.gavin.rxkotlin.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG_ACTIVITY_LOGIN = "ActivityLogin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSwipeBackEnable(false)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById(R.id.fab) as FloatingActionButton?
        fab!!.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }

        val _navigationView = findViewById(R.id.nav_view) as NavigationView?
        _navigationView!!.setNavigationItemSelectedListener(this)

        val _ivNavHeaderUserPic: ImageView = _navigationView.getHeaderView(0).findViewById(R.id.ivNavHeaderUserPic) as ImageView;
        _ivNavHeaderUserPic.setOnClickListener { enterIntoActivity(TAG_ACTIVITY_LOGIN) }

        initNav(drawerLayout, toolbar)
    }

    override fun setViewListener() {
    }

    fun initNav(drawerLayout: DrawerLayout, toolbar: Toolbar) {
        val _drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
//                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        drawerLayout.addDrawerListener(_drawerToggle as ActionBarDrawerToggle)
        drawerLayout.post(Runnable { (_drawerToggle as ActionBarDrawerToggle).syncState() })
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun enterIntoActivity(tag: String) {
        when (tag) {
            TAG_ACTIVITY_LOGIN -> startActivity<LoginActivity>()
        }
    }


    /*********************
     * Event Bus start
     *********************/
    fun onLoginSuccessEvent(event: LoginSuccessEvent) {
        tvLoginState.text = String.format(getString(R.string.login_state_success), event.userInfo.phoneNo)
    }
    /*********************
     * Event Bus end
     *********************/
}
