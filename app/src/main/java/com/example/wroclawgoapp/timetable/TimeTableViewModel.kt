package com.example.wroclawgoapp.timetable

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TimeTableUiState(
    val routes: List<Route>,
    val selectedRoute: Route? = null,
)

class TimeTableViewModel(routes: List<Route>): ViewModel() {
    private var  _uiState = MutableStateFlow(TimeTableUiState(routes))
    val uiState: StateFlow<TimeTableUiState> = _uiState.asStateFlow()

    fun updateSelectedRoute(selected: Route){
        _uiState.update { currentState ->
            currentState.copy(
                selectedRoute = selected,
            )
        }
    }

    fun clear(){
            _uiState.update { currentState-> currentState.copy(selectedRoute = null) }
    }
}