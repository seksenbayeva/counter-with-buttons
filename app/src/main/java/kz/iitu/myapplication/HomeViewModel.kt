package kz.iitu.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kz.iitu.myapplication.HomeContract.SideEffect
import kz.iitu.myapplication.HomeContract.UiAction
import kz.iitu.myapplication.HomeContract.UiState
import kz.iitu.myapplication.mvi.MVI
import kz.iitu.myapplication.mvi.mvi

class HomeViewModel : ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnIncreaseCountClick -> increaseCount()
            UiAction.OnDecreaseCountClick -> onDecreaseCountClick()
        }
    }

    private fun increaseCount() {
        updateUiState { copy(count = count + 1) }
    }

    private fun onDecreaseCountClick() {
        if (uiState.value.count > 0) {
            updateUiState { copy(count = count - 1) }
        } else {
            viewModelScope.emitSideEffect(SideEffect.ShowCountCanNotBeNegativeToast)
        }
    }
}

private fun initialUiState(): UiState = UiState(count = 0)