package com.example.practicarecyclerviewfrancisco.ui.detalle

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.DetalleFichaActivityBinding
import com.example.practicarecyclerviewfrancisco.ui.utils.StringProvider

class DetalleFichaActivity: AppCompatActivity (){

    private lateinit var binding: DetalleFichaActivityBinding

    private val viewModel: DetalleFichaViewModel by viewModels {
        DetalleFichaViewModelFactory(
            StringProvider.instances(this)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleFichaActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val intent : Intent = intent

            val selectedFicha = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("selectedFicha", FichaMascota::class.java)
            } else {
                TODO("VERSION.SDK_INT < TIRAMISU | Preguntar a oscar como quiere que lo hagamos")
            }

            if (selectedFicha != null) {
                viewModel.mostrarFichaSeleccionada(selectedFicha)
            }



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