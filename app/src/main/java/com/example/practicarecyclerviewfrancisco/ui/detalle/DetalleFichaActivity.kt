package com.example.practicarecyclerviewfrancisco.ui.detalle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.databinding.DetalleFichaActivityBinding
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.ui.utils.StringProvider

class DetalleFichaActivity: AppCompatActivity (){

    private lateinit var binding: DetalleFichaActivityBinding

    private val viewModel: DetalleFichaViewModel by viewModels {
        DetalleFichaViewModelFactory(
            StringProvider.instances(this),
            GetFichaMascotaListUsecase(FichaMascotaRepository()),
            GetFichaMascotaUsecase(FichaMascotaRepository())
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleFichaActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val intent : Intent = intent

            val fichaId = intent.getIntExtra("selectedFicha", 0)

            viewModel.getFichaMascotaUsecase(fichaId)







            /*val selectedFicha = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("selectedFicha", FichaMascota::class.java)
            } else {
                TODO("hay que cambiar esto para que en lugar de coger el objeto y lo pinte tiene que usar un use case que recupere la ficha con un id o algo parecido")
            }

            if (selectedFicha != null) {
                viewModel.mostrarFichaSeleccionada(selectedFicha)
            }*/



            viewModel.uiState.observe(this@DetalleFichaActivity){state ->
                state.mensaje?.let{ mensaje ->
                    Toast.makeText(this@DetalleFichaActivity, mensaje, Toast.LENGTH_SHORT).show()
                    viewModel.mensajeMostrado()
                }?: run {
                    editTextPropietario.setText(state.fichaMascota.propietario)
                    editTextEmail.setText(state.fichaMascota.email)
                    editTextPhone.setText(state.fichaMascota.telefono)
                    editTextNombreMascota.setText(state.fichaMascota.nombreMascota)
                    sliderComportamiento.value = state.fichaMascota.comportamiento
                    checkBoxEsterilizado.isChecked = state.fichaMascota.esterilizado
                    checkBoxVacunado.isChecked = state.fichaMascota.vacunado

                    //TODO("Preguntar si esto esta bien asi")
                    radioGato.isChecked= state.fichaMascota.especie == "Gato"
                    radioPerro.isChecked=state.fichaMascota.especie == "Perro"
                }
            }

        }

    }

}