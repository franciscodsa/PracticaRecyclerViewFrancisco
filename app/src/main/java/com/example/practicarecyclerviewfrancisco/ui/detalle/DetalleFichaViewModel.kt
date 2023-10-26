package com.example.practicarecyclerviewfrancisco.ui.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.ui.utils.StringProvider

class DetalleFichaViewModel (
    //TODO("Preguntar para que se usa ")
    private val stringProvider: StringProvider,
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase,
    private val getFichaMascotaUseCase : GetFichaMascotaUsecase

): ViewModel() {



    private val _uiState = MutableLiveData<DetalleFichaState>()

    val uiState : LiveData<DetalleFichaState> get() = _uiState

    fun mostrarFichaSeleccionada(fichaMascota: FichaMascota){
        _uiState.value = DetalleFichaState(fichaMascota = fichaMascota, mensaje = null)
    }

    fun getFichaMascotaUsecase(id : Int){
        val fichaMascota = getFichaMascotaUseCase.execute(id)

        _uiState.value = DetalleFichaState(fichaMascota = fichaMascota, mensaje = null)

    }

    fun mensajeMostrado(){
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }




}

class DetalleFichaViewModelFactory(
    private val stringProvider: StringProvider,
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase,
    private val getFichaMascotaUseCase : GetFichaMascotaUsecase

): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleFichaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST") return DetalleFichaViewModel(
                stringProvider,
                getFichaMascotaListUsecase,
                getFichaMascotaUseCase
            )as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}