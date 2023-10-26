package com.example.practicarecyclerviewfrancisco.domain.usecases

import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota

class GetFichaMascotaListUsecase (private val repository: FichaMascotaRepository) {
    fun execute() : List<FichaMascota>  {
        return  repository.getFichaMascotaList()
    }

}