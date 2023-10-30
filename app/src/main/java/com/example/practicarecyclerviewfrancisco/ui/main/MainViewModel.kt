package com.example.practicarecyclerviewfrancisco.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase

class MainViewModel(
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase
) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()

    val uiState: LiveData<MainState> get() = _uiState

    fun getFichaMascotaList(): List<FichaMascota> {
        return getFichaMascotaListUsecase.execute()
    }

    fun mostrarFichaMascotaList() {
        val fichaMascotalist = getFichaMascotaListUsecase.execute()

        _uiState.value = MainState(fichaMascotaList = fichaMascotalist)
    }

}

class MainViewModelFactory(
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                getFichaMascotaListUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}