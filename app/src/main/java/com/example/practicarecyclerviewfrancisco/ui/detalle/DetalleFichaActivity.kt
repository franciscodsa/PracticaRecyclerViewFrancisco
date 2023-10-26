package com.example.practicarecyclerviewfrancisco.ui.detalle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicarecyclerviewfrancisco.data.FichaMascotaRepository
import com.example.practicarecyclerviewfrancisco.data.model.FichaMascota
import com.example.practicarecyclerviewfrancisco.databinding.DetalleFichaActivityBinding
import com.example.practicarecyclerviewfrancisco.domain.usecases.AddFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.DeleteFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaListUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.GetFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.domain.usecases.UpdateFichaMascotaUsecase
import com.example.practicarecyclerviewfrancisco.ui.constants.ConstantesDetalle

class DetalleFichaActivity: AppCompatActivity (){

    private lateinit var binding: DetalleFichaActivityBinding

    private val viewModel: DetalleFichaViewModel by viewModels {
        DetalleFichaViewModelFactory(
            GetFichaMascotaListUsecase(FichaMascotaRepository()),
            GetFichaMascotaUsecase(FichaMascotaRepository()),
            AddFichaMascotaUsecase(FichaMascotaRepository()),
            DeleteFichaMascotaUsecase(FichaMascotaRepository()),
            UpdateFichaMascotaUsecase(FichaMascotaRepository())
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleFichaActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)
            viewModel

            //Muestra la ficha seleccionada en los campos
            viewModel.getFichaMascota(getSelectedFichaId())

            //Actualiza la ficha que se esta mostrando
            buttonUpdate.setOnClickListener {
                val updatedFichaMascota = createFichaMascotaFromInput()
                viewModel.updeateFicha(updatedFichaMascota)
            }

            //Elimina la ficha que se esta mostrando
            buttonDelete.setOnClickListener{
                viewModel.deleteFichaMascota(getSelectedFichaId())
            }

            //Agrega una ficha a la lista con los datos colocados en cada campo
            buttonAdd.setOnClickListener {
                val fichaMascota = createFichaMascotaFromInput()
                viewModel.addFichaMascota(fichaMascota)
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
                    radioGato.isChecked= state.fichaMascota.especie == ConstantesDetalle.especieGato
                    radioPerro.isChecked= state.fichaMascota.especie == ConstantesDetalle.especiePerro
                }
            }

        }

    }



    private fun getSelectedFichaId () : Int{
        val intent: Intent = intent

        val fichaId = intent.getIntExtra(ConstantesDetalle.intentName, 0)

        return fichaId
    }


    private fun createFichaMascotaFromInput(): FichaMascota {
        with(binding){
            val propietario = editTextPropietario.text.toString()
            val email = editTextEmail.text.toString()
            val telefono = editTextPhone.text.toString()
            val nombreMascota = editTextNombreMascota.text.toString()

            val especieSeleccionada = when {
                radioGato.isChecked -> ConstantesDetalle.especieGato
                radioPerro.isChecked -> ConstantesDetalle.especiePerro
                else -> ConstantesDetalle.especieNoSeleccionada
            }

            val comportamiento = sliderComportamiento.value
            val esterilizado = checkBoxEsterilizado.isChecked
            val vacunado = checkBoxVacunado.isChecked

            val id = if (buttonUpdate.isPressed) getSelectedFichaId() else viewModel.getNextId()


            return FichaMascota(
                id,
                propietario,
                email,
                telefono,
                nombreMascota,
                especieSeleccionada,
                esterilizado,
                vacunado,
                comportamiento
            )
        }

    }

}