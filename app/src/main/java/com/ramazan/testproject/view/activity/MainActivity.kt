package com.ramazan.testproject.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramazan.testproject.databinding.ActivityMainBinding
import com.ramazan.testproject.view.adapter.CoursesAdapter
import com.ramazan.testproject.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()
    private val adapter = CoursesAdapter { id -> vm.onToggleFavorite(id) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collect { state -> adapter.submit(state.courses, state.favoriteIds) }
            }
        }
    }
}