package com.example.jason.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.jason.movierater.LandingPage.Companion.movieList
import kotlinx.android.synthetic.main.activity_edit_movie_details.*

class EditMovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie_details)

        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true)


        var movieNo = intent.getIntExtra("order",0)


        nameET.setText(movieList[movieNo].title)
        descriptionET.setText(movieList[movieNo].description)

        if(movieList[movieNo].language == "English") {
            EnglishLanguage.isChecked = true
        }
        else if (movieList[movieNo].language == "Chinese") {
            ChineseLanguage.isChecked = true
        }
        else if (movieList[movieNo].language == "Malay") {
            MalayLanguage.isChecked = true
        }
        else if (movieList[movieNo].language == "Tamil") {
            TamilLanguage.isChecked = true
        }

        releaseDateET.setText(movieList[movieNo].releaseDate)

        if(movieList[movieNo].suitable)
        {
            checkboxCB.isChecked == true
            violenceID.visibility = View.VISIBLE
            languageUsedID.visibility = View.VISIBLE

            if(movieList[movieNo].violence)
                violenceID.isChecked = true

            if(movieList[movieNo].languageUsed)
                languageUsedID.isChecked = true
        }

        checkboxCB.setOnClickListener( {
            if (checkboxCB.isChecked == true) {
                violenceID.visibility = View.VISIBLE
                languageUsedID.visibility = View.VISIBLE
            } else {
                violenceID.visibility = View.INVISIBLE
                languageUsedID.visibility = View.INVISIBLE
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var myIntent = Intent(this, MainActivity_View::class.java)
        if (item?.itemId == R.id.menuSave) {
            var validate = true
            if (nameET.text.isEmpty()) {
                validate = false
                nameET.setError("Field empty")
            }
            if (descriptionET.text.isEmpty()) {
                validate = false
                descriptionET.setError("Field empty")
            }
            if (releaseDateET.text.isEmpty()) {
                validate = false
                releaseDateET.setError("Field empty")
            }
            if (validate) {
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

                var movieNo = intent.getIntExtra("order",0)

                movieList[movieNo].title = nameET.text.toString()
                movieList[movieNo].description = descriptionET.text.toString()
                movieList[movieNo].language = language
                movieList[movieNo].releaseDate = releaseDateET.text.toString()
                movieList[movieNo].suitable = checkboxCB.isChecked
                movieList[movieNo].violence = violenceID.isChecked
                movieList[movieNo].languageUsed = languageUsedID.isChecked

                startActivity(myIntent)
                finish()
            }
        }
        else if (item?.itemId == R.id.menuCancel) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return super.onSupportNavigateUp()
    }
}
