package com.example.practicarecyclerviewfrancisco.ui.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.ui.utils.StringProvider

class DetalleFichaViewModel (
    //TODO("Preguntar para que se usa esto y si las funcionalidades de los detalles de la ficha tienen que implementarse con una lista, no se como hacer services con el repository que hice con el json")
    private val stringProvider: StringProvider
): ViewModel() {

    private val _uiState = MutableLiveData<DetalleFistaState>()

    val uiState : LiveData<DetalleFistaState> get() = _uiState

    fun mostrarFichaSeleccionada(fichaMascota: FichaMascota){
        _uiState.value = DetalleFistaState(fichaMascota = fichaMascota, mensaje = null)
    }

    fun mensajeMostrado(){
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }


}

class DetalleFichaViewModelFactory(
    private val stringProvider: StringProvider
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleFichaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST") return DetalleFichaViewModel(
                stringProvider
            )as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}