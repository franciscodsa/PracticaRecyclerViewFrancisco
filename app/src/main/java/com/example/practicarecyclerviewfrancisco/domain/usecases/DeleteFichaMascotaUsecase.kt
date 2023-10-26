package com.example.practicarecyclerviewfrancisco.domain.usecases

import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

class DeleteFichaMascotaUsecase(private val repository: FichaMascotaRepository) {
    fun excute(id: Int){
        repository.deleteFicha(id)
    }
}