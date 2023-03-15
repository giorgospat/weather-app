package com.example.presentation.features.add_location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.feature.location.search.LocationSearchUseCase
import com.example.domain.feature.location.store.LocationStoreUseCase
import com.example.domain.model.LocationModel
import com.example.presentation.features.add_location.model.AddLocationMessage
import com.example.presentation.snackbar.SnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    internal val snackBar: SnackBar,
    private val storeUseCase: LocationStoreUseCase,
    private val searchUseCase: LocationSearchUseCase
) : ViewModel() {

    private var _searchTerm = MutableStateFlow("")
    internal val searchTerm = _searchTerm.asStateFlow()

    private var _infoMessage = Channel<AddLocationMessage>(Channel.BUFFERED)
    internal val infoMessage = _infoMessage.receiveAsFlow()

    private var _searchResults = MutableStateFlow(emptyList<LocationModel>())
    internal val searchResults = _searchResults.asStateFlow()

    private var searchJob: Job? = null


    internal fun onClickSave(location: LocationModel) {

        viewModelScope.launch {
            storeUseCase.save(location)
            _infoMessage.send(AddLocationMessage.Save)
        }
    }

    internal fun onSearchChange(term: String) {
        _searchTerm.value = term

        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            delay(200) // search delay

            searchUseCase.search(location = term).fold(
                onSuccess = { results ->
                    _searchResults.value = results
                },
                onFailure = {
                    _infoMessage.send(AddLocationMessage.ResultError)
                }
            )
        }

    }

}