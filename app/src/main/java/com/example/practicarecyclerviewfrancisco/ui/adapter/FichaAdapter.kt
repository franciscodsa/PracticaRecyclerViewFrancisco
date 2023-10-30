package com.example.practicarecyclerviewfrancisco.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.practicarecyclerviewfrancisco.R
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.ItemFichaBinding
import com.example.practicarecyclerviewfrancisco.ui.commons.ConstantesUi
import timber.log.Timber
import java.io.IOException

class FichaAdapter(
    private var fichaMascotaList: List<FichaMascota>,
    private val onClickListener: (FichaMascota) -> Unit
) : RecyclerView.Adapter<FichaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FichaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FichaViewHolder(layoutInflater.inflate(R.layout.item_ficha, parent, false))
    }

    override fun getItemCount(): Int {
        return fichaMascotaList.size
    }

    override fun onBindViewHolder(holder: FichaViewHolder, position: Int) {
        holder.render(fichaMascotaList[position], onClickListener)
    }

    fun cambiarLista(lista: List<FichaMascota>) {
        fichaMascotaList = lista
        notifyDataSetChanged()
    }

}

class FichaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemFichaBinding.bind(view)
    fun render(fichaMascota: FichaMascota, onClickListener: (FichaMascota) -> Unit) {
        with(binding) {
            tvNombrePropietario.text = fichaMascota.propietario
            tvNombreMascota.text = fichaMascota.nombreMascota
            try {
                ivMascota.load(fichaMascota.imagen)
            } catch (e: IOException) {
                ivMascota.load(ConstantesUi.defaultImage)
                Timber.e(e, ConstantesUi.errorCargandoImagen)
            }

            buttonMasInfo.setOnClickListener {
                onClickListener(fichaMascota)
            }
        }

    }
}