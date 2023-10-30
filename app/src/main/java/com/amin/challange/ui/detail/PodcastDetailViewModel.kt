package com.amin.challange.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amin.challange.data.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastDetailViewModel(podcast: Podcast):ViewModel(){
    private val _state = MutableStateFlow(PodcastDetailViewState(podcast))
    val state: StateFlow<PodcastDetailViewState>
        get() = _state
    init {
        viewModelScope.launch {
            //fetch detail api if needed but here we don't need it
            _state.value = PodcastDetailViewState(podcast)
        }
    }

    data class PodcastDetailViewState(
        val podcastDetail: Podcast,

    )


}