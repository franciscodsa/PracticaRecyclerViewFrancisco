package com.example.practicarecyclerviewfrancisco.data

import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type

class FichaMascotaRepository (file : InputStream? = null){

    companion object{
        private val fichaMascotaList = mutableListOf<FichaMascota>()
    }

    init {
        if (fichaMascotaList.size == 0){
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

            val listOfCardsType : Type = Types.newParameterizedType(
                MutableList::class.java,
                FichaMascota::class.java
            )
            val fichaMascota = file?.bufferedReader()?.readText()?.let { contenidoFichero -> moshi.adapter<List<FichaMascota>>(listOfCardsType).fromJson(contenidoFichero) }

            fichaMascota?.let { fichaMascotaList.addAll(it) }
        }
    }

    fun getFichaMascotaList() : List<FichaMascota>{
        return fichaMascotaList
    }

    fun getFicha(id : Int) : FichaMascota{
        return fichaMascotaList[id]
    }

    fun addFicha(fichaMascota: FichaMascota){
        fichaMascotaList.add(fichaMascota)
    }


}