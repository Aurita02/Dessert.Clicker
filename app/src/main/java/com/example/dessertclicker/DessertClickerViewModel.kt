package com.example.dessertclicker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource

data class DessertClickerUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertImageId: Int = Datasource.dessertList.first().imageId
)

class DessertClickerViewModel : ViewModel() {

    private val desserts = Datasource.dessertList

    var uiState by mutableStateOf(DessertClickerUiState())
        private set

    fun onDessertClicked() {
        val currentDessert = determineDessertToShow(
            desserts,
            uiState.dessertsSold
        )

        val updatedDessertsSold = uiState.dessertsSold + 1

        val nextDessert = determineDessertToShow(
            desserts,
            updatedDessertsSold
        )

        uiState = uiState.copy(
            revenue = uiState.revenue + currentDessert.price,
            dessertsSold = updatedDessertsSold,
            currentDessertImageId = nextDessert.imageId
        )
    }
}
