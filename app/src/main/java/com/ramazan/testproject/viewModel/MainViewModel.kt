package com.ramazan.testproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazan.domain.model.Course
import com.ramazan.domain.usecase.GetFavoriteIdsFlow
import com.ramazan.domain.usecase.ToggleFavorite
import com.ramazan.network.repository.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeFavs: GetFavoriteIdsFlow,
    private val toggle: ToggleFavorite,
    private val repository: CoursesRepository
) : ViewModel() {

    private val courses = MutableStateFlow<List<Course>>(emptyList())

    val uiState: StateFlow<UiState> = combine(courses, observeFavs()) { list, favs ->
        UiState(list, favs)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())

    init {
        viewModelScope.launch {
            val loaded = repository.loadCourses()
            courses.value = loaded
        }
    }

    fun onToggleFavorite(id: Long) {
        viewModelScope.launch { toggle(id) }
    }
}