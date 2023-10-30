package com.example.practicarecyclerviewfrancisco.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.ActivityMainBinding
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase
import com.example.practicarecyclerviewfrancisco.ui.detalle.DetalleFichaActivity
import com.example.practicarecyclerviewfrancisco.ui.adapter.FichaAdapter
import com.example.practicarecyclerviewfrancisco.ui.commons.ConstantesUi

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FichaAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetFichaMascotaListUsecase(FichaMascotaRepository(assets.open(ConstantesUi.dataJson)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            recyclerFicha.layoutManager = LinearLayoutManager(this@MainActivity)

            val fichaMascotaList = viewModel.getFichaMascotaList()

            adapter = FichaAdapter(fichaMascotaList, ::onItemSelected)


            fichaMascotaList.let {
                recyclerFicha.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerFicha.adapter = adapter
            }
        }
        viewModel.uiState.observe(this@MainActivity) { state ->
            val fichaMascotaList = state.fichaMascotaList
            adapter.cambiarLista(fichaMascotaList)
        }

    }

    fun onItemSelected(fichaMascota: FichaMascota) {
        val intent = Intent(this, DetalleFichaActivity::class.java)
        intent.putExtra(ConstantesUi.intentName, fichaMascota.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFichaMascotaList()
    }

    /*fun initRecyclerView(fichaMascotaList: List<FichaMascota>) {
    binding.recyclerFicha.layoutManager = LinearLayoutManager(this)
    binding.recyclerFicha.adapter =  }*/
}






