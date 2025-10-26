package com.ramazan.testproject.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ramazan.domain.model.Course

object CourseDiff : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean =
        oldItem == newItem
}