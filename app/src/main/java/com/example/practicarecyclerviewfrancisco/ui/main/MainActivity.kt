package com.example.practicarecyclerviewfrancisco.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.ActivityMainBinding
import com.example.practicarecyclerviewfrancisco.ui.detalle.DetalleFichaActivity
import com.example.practicarecyclerviewfrancisco.ui.adapter.FichaAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    fun initRecyclerView(){
        binding.recyclerFicha.layoutManager = LinearLayoutManager(this)
        binding.recyclerFicha.adapter = FichaAdapter(FichaMascotaRepository(assets.open("data.json")).getFichaMascotaList()) { fichaMascota ->
            onItemSelected(
                fichaMascota
            )
        }
    }

    fun onItemSelected(fichaMascota: FichaMascota){
        val intent = Intent(this, DetalleFichaActivity::class.java)
        intent.putExtra("selectedFicha", fichaMascota.id)
        startActivity(intent)
    }

    //TODO("hay que agregar onResume para implemetar el cambiar lista para que cuando se elimine se actualice la lista")

}