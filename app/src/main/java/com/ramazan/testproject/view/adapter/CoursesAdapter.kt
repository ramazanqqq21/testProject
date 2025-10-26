package com.ramazan.testproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramazan.domain.model.Course
import com.ramazan.testproject.R

class CoursesAdapter(
    private val onToggle: (Long) -> Unit
) : ListAdapter<Course, CoursesAdapter.VH>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bind(item, item.hasLike, onToggle)
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val author: TextView = itemView.findViewById(R.id.author)

        fun bind(item: Course, favorite: Boolean, onToggle: (Long) -> Unit) {
            title.text = (if (favorite) "★ " else "☆ ") + item.title
            author.text = item.text // или item.author, если есть
            itemView.setOnClickListener { onToggle(item.id) }
        }
    }
}