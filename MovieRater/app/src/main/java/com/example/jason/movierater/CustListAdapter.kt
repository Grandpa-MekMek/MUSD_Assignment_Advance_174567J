package com.example.jason.movierater

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class CustListAdapter(context: Context, data:ArrayList<Movie>) : BaseAdapter() {
    var sList = data
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v: View
        v = this.mInflator.inflate(R.layout.custom_list_view, parent, false)

        val label: TextView = v.findViewById(R.id.label)
        val image: ImageView = v.findViewById(R.id.image)
        label.text = sList[position].title
        image.setImageResource(R.drawable.movierater)

        return v
    }

    override fun getItem(position: Int): Any {
        return sList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
      return sList.count()
    }

}