package com.amin.challange.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amin.challange.data.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastListViewModel:ViewModel(){
    private val _state = MutableStateFlow(PodcastsViewState())
    val state: StateFlow<PodcastsViewState>
        get() = _state
    init {
        viewModelScope.launch {
            //Todo fetch Data from api or maybe in future first DB then call api !??!?!
            _state.value = PodcastsViewState()
        }
    }

    data class PodcastsViewState(
        val topPodcasts: List<Podcast> = emptyList(),

    )
}