package com.example.jason.movierater

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import kotlinx.android.synthetic.main.activity_main_view.*
import java.lang.Error

class MainActivity_View : AppCompatActivity() {
    val MAIN_ACTIVITY_VIEW_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)


        var titleFromAdd = intent.getStringExtra("title")
        var descriptionFromAdd = intent.getStringExtra("description")
        var languageFromAdd = intent.getStringExtra("language")
        var releaseDateFromAdd = intent.getStringExtra("releaseDate")
        var suitableFromAdd = intent.getBooleanExtra("suitable", false)
        var violenceFromAdd = intent.getBooleanExtra("violence", false)
        var languageUsedFromAdd = intent.getBooleanExtra("languageUsed", false)

        val movie = Movie(titleFromAdd, descriptionFromAdd, releaseDateFromAdd, languageFromAdd, suitableFromAdd, violenceFromAdd, languageUsedFromAdd)

        titleTV.text = movie.title
        overviewTV.text = movie.description
        languageTV.text = movie.language
        release_dateTV.text = movie.releaseDate

        var suitableReason = ""
        if(movie.suitable) {
            if(movie.violence && movie.languageUsed) {
                suitableReason = "No (Violence, Language Used)"
            }
            else if(movie.violence) {
                suitableReason = "No (Violence)"
            }
            else if(movie.languageUsed) {
                suitableReason = "No (Language Used)"
            }
            else {
                suitableReason = "No"
            }
        }
        else {
            suitableReason = "Yes"
        }


        suitableTV.text = suitableReason



        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true)

        registerForContextMenu(tvReview)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(1, 1001, 1, "Add Review")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == 1001) {
            var myIntent = Intent(this, MainActivity_Rate::class.java)
            myIntent.putExtra("title", intent.getStringExtra("title"))
            startActivityForResult(myIntent, MAIN_ACTIVITY_VIEW_REQUEST_CODE)
        }

        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == MAIN_ACTIVITY_VIEW_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                var reviewMsg = data?.getStringExtra(MainActivity_Rate.RETURN_MSG) as String
                var reviewRating = data?.getFloatExtra(MainActivity_Rate.RETURN_RATING, 0f)
                ratingBar.visibility = View.VISIBLE
                ratingBar.rating = reviewRating
                tvReview.text = reviewMsg
                unregisterForContextMenu(tvReview)
            }
        }
    }
}