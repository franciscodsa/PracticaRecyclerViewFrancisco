package com.example.practicarecyclerviewfrancisco.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicarecyclerviewfrancisco.databinding.DetalleFichaActivityBinding

class DetalleFichaActivity: AppCompatActivity (){

    private lateinit var binding: DetalleFichaActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleFichaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}