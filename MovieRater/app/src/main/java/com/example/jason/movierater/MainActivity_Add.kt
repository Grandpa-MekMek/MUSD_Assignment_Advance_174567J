package com.example.jason.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.jason.movierater.LandingPage.Companion.movieList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity_Add : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkboxCB.setOnClickListener( {
            if (checkboxCB.isChecked == true) {
                violenceID.visibility = View.VISIBLE
                languageUsedID.visibility = View.VISIBLE
            } else {
                violenceID.visibility = View.INVISIBLE
                languageUsedID.visibility = View.INVISIBLE
            }
        })

        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var myIntent = Intent(this, MainActivity_View::class.java)

        if(item?.itemId == R.id.menuAdd) {

            var validate = true
            if(nameET.text.isEmpty()) {
                validate = false
                nameET.setError("Field empty")
            }
            if(descriptionET.text.isEmpty()) {
                validate = false
                descriptionET.setError("Field empty")
            }
            if(releaseDateET.text.isEmpty()) {
                validate = false
                releaseDateET.setError("Field empty")
            }
            if(validate) {
                myIntent.putExtra("title", nameET.text.toString())
                myIntent.putExtra("description", descriptionET.text.toString())
                var language = ""
                if (EnglishLanguage.isChecked) {
                    language = "English"
                } else if (ChineseLanguage.isChecked) {
                    language = "Chinese"
                } else if (MalayLanguage.isChecked) {
                    language = "Malay"
                } else if (TamilLanguage.isChecked) {
                    language = "Tamil"
                }
                myIntent.putExtra("language", language)
                myIntent.putExtra("releaseDate", releaseDateET.text.toString())
                myIntent.putExtra("suitable", checkboxCB.isChecked)
                myIntent.putExtra("violence", violenceID.isChecked)
                myIntent.putExtra("languageUsed", languageUsedID.isChecked)

                val m = Movie(nameET.text.toString(), descriptionET.text.toString(), language, releaseDateET.text.toString(), checkboxCB.isChecked, violenceID.isChecked, languageUsedID.isChecked)

                movieList.add(m)

                startActivity(myIntent)
                finish()
            }
        }
        else if (item?.itemId == R.id.menuClearEntries) {
            nameET.setText("")
            descriptionET.setText("")
            releaseDateET.setText("")
            EnglishLanguage.isChecked = true
            ChineseLanguage.isChecked = false
            MalayLanguage.isChecked = false
            TamilLanguage.isChecked = false
            checkboxCB.isChecked = false
            violenceID.isChecked = false
            languageUsedID.isChecked = false
            violenceID.visibility = View.INVISIBLE
            languageUsedID.visibility = View.INVISIBLE
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return super.onSupportNavigateUp()
    }

}
