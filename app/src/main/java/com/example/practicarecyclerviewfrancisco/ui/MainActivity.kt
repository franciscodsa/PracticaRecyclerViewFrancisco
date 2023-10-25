package com.example.practicarecyclerviewfrancisco.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.ActivityMainBinding

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
        /*Toast.makeText(this, fichaMascota.especie, Toast.LENGTH_SHORT).show()*/
        val intent = Intent(this, DetalleFichaActivity::class.java)
         startActivity(intent)
    }

}