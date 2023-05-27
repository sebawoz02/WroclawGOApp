package com.example.wroclawgoapp.timetable

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RouteUiState(
    val routes: List<Route>,
    val selectedRoute: Route?
)

class RouteViewModel(routes: List<Route>): ViewModel() {
    private var  _uiState = MutableStateFlow(RouteUiState(routes,null))
    val uiState: StateFlow<RouteUiState> = _uiState.asStateFlow()

    fun updateSelectedRoute(selected: Route){
        _uiState.update { currentState ->
            currentState.copy(
                routes = uiState.value.routes,
                selectedRoute = selected
            )
        }
    }
}