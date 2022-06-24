package com.test.movies.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.movies.R
import com.test.movies.domain.model.Results

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.VH>() {

    private var items: ArrayList<Results> = arrayListOf()


    override fun getItemCount() = items.size

    fun addItems(newItems: List<Results>){
        items.addAll(newItems)
        notifyItemRangeInserted(items.count(), newItems.count())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_film, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val imgFilm: ImageView = itemView.findViewById(R.id.imgFilm)
        private val desc: TextView = itemView.findViewById(R.id.description)

        fun bind(it: Results) {
            itemView.run {
                title.text = it.displayTitle
                desc.text = it.summaryShort

                Glide.with(this).load(it.multimedia?.src).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgFilm)
            }
        }

    }
}