package com.ramazan.testproject.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramazan.domain.model.Course
import com.ramazan.testproject.R

class CoursesAdapter(
    private val onToggle: (Long) -> Unit
) : ListAdapter<Course, CoursesAdapter.VH>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_courses, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bind(item, item.hasLike, onToggle)
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val text: TextView = itemView.findViewById(R.id.tvText)
        private val price: TextView = itemView.findViewById(R.id.tvPrice)
        private val rate: TextView = itemView.findViewById(R.id.tvRate)
        private val startDate: TextView = itemView.findViewById(R.id.tvStartDate)

        private lateinit var publishDate: String
        private val ibFavorite: ImageButton = itemView.findViewById(R.id.ibFavorite)

        @SuppressLint("SetTextI18n")
        fun bind(item: Course, favorite: Boolean, onToggle: (Long) -> Unit) {
            title.text = item.title
            text.text = item.text
            price.text = item.price + " ₽"
            rate.text = item.rate
            startDate.text = item.startDate
            publishDate = item.publishDate

            val drawable = ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite)?.mutate()
            val color = if (favorite) {
                Color.parseColor("#12B956")
            } else {
                Color.parseColor("#FFFFFFFF")
            }
            drawable?.setTint(color)
            ibFavorite.setImageDrawable(drawable)

            ibFavorite.setOnClickListener { onToggle(item.id) }
        }
    }
}

