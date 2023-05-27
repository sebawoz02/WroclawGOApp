package com.example.wroclawgoapp.timetable

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TimeTableUiState(
    val routes: List<Route>,
    val stops: List<Stop>,

    val selectedRoute: Route? = null,
    val selectedStop: Stop? = null,
    val timeTable: TimeTable? = null
)

class TimeTableViewModel(routes: List<Route>, stops: List<Stop>): ViewModel() {
    private var  _uiState = MutableStateFlow(TimeTableUiState(routes, stops))
    val uiState: StateFlow<TimeTableUiState> = _uiState.asStateFlow()

    fun updateSelectedRoute(selected: Route){
        _uiState.update { currentState ->
            currentState.copy(
                selectedRoute = selected,
            )
        }
    }

    fun updateSelectedStop(selected: Stop){
        _uiState.update { currentState ->
            currentState.copy(
                selectedStop = selected
            )
        }
    }

    fun updateTimeTable(timeTable: TimeTable){
        _uiState.update { currentState -> currentState.copy(timeTable = timeTable) }
    }

    fun clear(){
        if(uiState.value.selectedStop == null && uiState.value.selectedRoute == null){
            _uiState.update { currentState-> currentState.copy(timeTable = null) }
        }

        else if(uiState.value.selectedStop != null){
            _uiState.update { currentState-> currentState.copy(selectedStop = null) }
        }

        else{
            _uiState.update { currentState-> currentState.copy(selectedRoute = null) }
        }


    }
}