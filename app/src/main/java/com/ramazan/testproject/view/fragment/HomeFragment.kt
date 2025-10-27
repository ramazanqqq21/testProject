package com.ramazan.testproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramazan.testproject.R
import com.ramazan.testproject.view.adapter.CoursesAdapter
import com.ramazan.testproject.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.activity_home) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CoursesAdapter
    private var isSortedDescending = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CoursesAdapter(onToggle = viewModel::onToggleFavorite)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val btnSort = view.findViewById<View>(R.id.btnSort)
        btnSort.setOnClickListener {
            isSortedDescending = !isSortedDescending
            viewModel.sortByDate(descending = isSortedDescending)
        }

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sortedCourses.collect { list ->
                    println(list)
                    println(list.size)
                    adapter.submitList(list)
                }
            }
        }
    }
}