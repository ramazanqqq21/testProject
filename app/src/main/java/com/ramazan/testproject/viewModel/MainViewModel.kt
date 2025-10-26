package com.ramazan.testproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazan.domain.model.Course
import com.ramazan.domain.usecase.GetFavoriteIdsFlow
import com.ramazan.domain.usecase.ToggleFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    observeFavs: GetFavoriteIdsFlow,
    private val toggle: ToggleFavorite
) : ViewModel() {
    private val courses = MutableStateFlow(
        listOf(
            Course(1, "Android Basics", "Jake"),
            Course(2, "Kotlin Coroutines", "Elena"),
            Course(3, "Jetpack Navigation", "Dan"),
            Course(4, "Clean Architecture", "Robert"),
        )
    )
    val uiState: StateFlow<UiState> = combine(courses, observeFavs()) { list, favs -> UiState(list, favs) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())
    fun onToggleFavorite(id: Long) { viewModelScope.launch { toggle(id) } }
}


data class UiState(val courses: List<Course> = emptyList(), val favoriteIds: Set<Long> = emptySet())