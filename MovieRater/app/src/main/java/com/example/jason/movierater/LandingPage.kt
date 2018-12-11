package com.example.jason.movierater

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPage : AppCompatActivity() {
    companion object {
        val movieList = arrayListOf<Movie>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        if(movieList.isNotEmpty()) {

            lv_movielist.visibility = View.VISIBLE
            val listItems = arrayOfNulls<String>(movieList.size)

            for(i in movieList.indices) {
                listItems[i] = movieList[i].title
            }

            val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            lv_movielist.adapter = listAdapter

        }

        var myIntent = Intent(this, MainActivity_View::class.java)

        lv_movielist.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                myIntent.putExtra("title", movieList[pos].title)
                myIntent.putExtra("description", movieList[pos].description)
                myIntent.putExtra("language", movieList[pos].language)
                myIntent.putExtra("releaseDate", movieList[pos].releaseDate)
                myIntent.putExtra("suitable", movieList[pos].suitable)
                myIntent.putExtra("violence", movieList[pos].violence)
                myIntent.putExtra("languageUsed", movieList[pos].languageUsed)
                startActivity(myIntent)
            }
        }

        registerForContextMenu(lv_movielist)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(movieList.isNotEmpty()) {
            var info = menuInfo as AdapterView.AdapterContextMenuInfo
            var pos = lv_movielist.getItemIdAtPosition(info.position).toInt()
                for(i in movieList.indices) {
                    if(i == pos) {
                        menu?.add(1, pos, 1, "Edit")
                }
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        for(i in movieList.indices) {
            if(item?.itemId == i) {
                var myIntent = Intent(this, EditMovieDetails::class.java)
                myIntent.putExtra("order", i)
                startActivity(myIntent)
            }
        }


        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landing_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.lp_menuAdd) {
            var myIntent = Intent(this, MainActivity_Add::class.java)
            startActivity(myIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        if(movieList.isNotEmpty()) {

            lv_movielist.visibility = View.VISIBLE
//            val listItems = arrayOfNulls<String>(movieList.size)
//
//            for(i in movieList.indices) {
//                listItems[i] = movieList[i].title
//            }

//            val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
//            lv_movielist.adapter = listAdapter

            //Requires Custom Adapter for Image?
            val adapter = CustListAdapter(this, movieList)
            lv_movielist.adapter = adapter
        }
    }
}

/* References
    * Week 13 MUSD Lecture 11 - Adapter Views Slides
    * https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin
    * https://www.youtube.com/watch?v=fwwu2mDD4cw
 */
