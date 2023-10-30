package com.example.practicarecyclerviewfrancisco.ui.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.domain.usecases.AddFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.DeleteFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.UpdateFichaMascotaUsecase

class DetalleFichaViewModel(
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase,
    private val getFichaMascotaUseCase: GetFichaMascotaUsecase,
    private val addFichaMascotaUsecase: AddFichaMascotaUsecase,
    private val deleteFichaMascotaUsecase: DeleteFichaMascotaUsecase,
    private val updateFichaMascotaUsecase: UpdateFichaMascotaUsecase
) : ViewModel() {


    private val _uiState = MutableLiveData<DetalleFichaState>()

    val uiState: LiveData<DetalleFichaState> get() = _uiState

    fun getNextId(): Int {
        val list = getFichaMascotaListUsecase.execute()
        val maxId = list.maxByOrNull { it.id }?.id ?: 0
        return maxId + 1
    }

    fun addFichaMascota(fichaMascota: FichaMascota) {
        addFichaMascotaUsecase.execute(fichaMascota)

        _uiState.value = _uiState.value?.copy(mensaje = ConstantesDetalle.mensajeFichaAdded)
    }

    fun deleteFichaMascota(id: Int) {

        deleteFichaMascotaUsecase.excute(id)

        _uiState.value = DetalleFichaState(mensaje = ConstantesDetalle.mensajeFichaEliminada)
    }

    fun mostrarFichaSeleccionada(id: Int) {
        val fichaMascota = getFichaMascotaUseCase.execute(id)

        _uiState.value = DetalleFichaState(fichaMascota = fichaMascota, mensaje = null)
    }

    fun getFichaMascota(id: Int): FichaMascota {
        return getFichaMascotaUseCase.execute(id)
    }

    fun updeateFicha(fichamascotaUpdated: FichaMascota) {
        updateFichaMascotaUsecase.execute(fichamascotaUpdated)
        _uiState.value = DetalleFichaState(
            fichaMascota = fichamascotaUpdated,
            mensaje = ConstantesDetalle.mensajeFichaActualizada
        )
    }

    fun mensajeMostrado() {
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }

}

class DetalleFichaViewModelFactory(
    private val getFichaMascotaListUsecase: GetFichaMascotaListUsecase,
    private val getFichaMascotaUseCase: GetFichaMascotaUsecase,
    private val addFichaMascotaUsecase: AddFichaMascotaUsecase,
    private val deleteFichaMascotaUsecase: DeleteFichaMascotaUsecase,
    private val updateFichaMascotaUsecase: UpdateFichaMascotaUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleFichaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return DetalleFichaViewModel(
                getFichaMascotaListUsecase,
                getFichaMascotaUseCase,
                addFichaMascotaUsecase,
                deleteFichaMascotaUsecase,
                updateFichaMascotaUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}