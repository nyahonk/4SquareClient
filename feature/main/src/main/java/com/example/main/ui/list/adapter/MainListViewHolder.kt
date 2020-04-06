package com.example.main.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.main.data.entities.venues.Venue
import kotlinx.android.synthetic.main.rv_item_venue.view.*

class MainListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(venue: Venue) {
        itemView.rv_venue_title.text = venue.name
        itemView.rv_venue_subtitle.text = makeFullAddress(venue.venueLocation?.formattedAddress!!)
    }

    private fun makeFullAddress(formattedAdress: List<String>): String {

        val sb = StringBuilder()
        formattedAdress.forEach{sb.append(it)}
        return sb.toString()
    }
}