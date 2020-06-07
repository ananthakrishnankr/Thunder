package com.akr.thunder

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        centerToolbarTitle(toolbar)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    private fun centerToolbarTitle(toolbar: Toolbar) {
        val title = toolbar.title
        val outViews: ArrayList<View> = ArrayList(1)
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT)
        if (outViews.isNotEmpty()) {
            val titleView = outViews[0] as TextView
            titleView.gravity = Gravity.CENTER
            val layoutParams =
                titleView.layoutParams as Toolbar.LayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            toolbar.requestLayout()
            //also you can use titleView for changing font: titleView.setTypeface(Typeface);
        }
    }
}
