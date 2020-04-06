package com.example.main.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import com.example.main.data.entities.venues.Venue

class MainListAdapter(var venues: List<Venue>) : RecyclerView.Adapter<MainListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_venue, parent, false)
        )


    override fun getItemCount() = venues.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(venues[position])
    }
}