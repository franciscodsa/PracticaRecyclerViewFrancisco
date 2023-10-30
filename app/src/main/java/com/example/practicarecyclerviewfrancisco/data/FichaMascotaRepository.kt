package com.example.practicarecyclerviewfrancisco.data

import com.example.practicarecyclerviewfrancisco.domain.model.FichaMascota
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FichaMascotaRepository (file : InputStream? = null){

    class LocalDateAdapter {
        companion object {
            @ToJson
            fun toJson(value: LocalDate): String {
                return FORMATTER.format(value)
            }

            @FromJson
            fun fromJson(value: String): LocalDate {
                return LocalDate.from(FORMATTER.parse(value))
            }

            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        }
    }

    companion object{
        private val fichaMascotaList = mutableListOf<FichaMascota>()
        private val fichaMascotaVacia = FichaMascota(-1, "", "","", "", "", false, false, 0.0f, "", LocalDate.now())
    }

    init {
        if (fichaMascotaList.isEmpty()){
            val moshi = Moshi.Builder()
                .add(LocalDateAdapter)
                .addLast(KotlinJsonAdapterFactory())
                .build()

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

    fun getFicha(id: Int): FichaMascota {
        return fichaMascotaList.find { it.id == id } ?: fichaMascotaVacia
    }

    fun addFicha(fichaMascota: FichaMascota){
        fichaMascotaList.add(fichaMascota)
    }

    fun deleteFicha(id : Int){
        fichaMascotaList.removeAll{ ficha -> ficha.id == id}
    }

    fun updateFicha(fichaMascotaUpdated: FichaMascota){
        val index = fichaMascotaList.indexOfFirst { ficha -> ficha.id == fichaMascotaUpdated.id }

        if (index != -1) fichaMascotaList[index] = fichaMascotaUpdated
    }


}