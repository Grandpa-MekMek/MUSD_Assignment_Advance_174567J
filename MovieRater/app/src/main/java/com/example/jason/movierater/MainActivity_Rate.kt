package com.example.jason.movierater

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import kotlinx.android.synthetic.main.activity_main_rate.*

class MainActivity_Rate : AppCompatActivity() {

    companion object {
        val RETURN_MSG = "message"
        val RETURN_RATING = "rating"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rate)

        var titleFromReview = intent.getStringExtra("title")

        tvTitleRate.text = "${tvTitleRate.text}" + titleFromReview

        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.review_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.menuSubmit) {
            var output = Intent()
            output.putExtra(RETURN_MSG, etReview.text.toString())
            output.putExtra(RETURN_RATING, rateBar_Review.rating)
            setResult(Activity.RESULT_OK, output)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
