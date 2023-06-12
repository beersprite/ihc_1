package com.example.ihc_1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Use a barra de navegação para acessar cada tarefa."
    }
    val text: LiveData<String> = _text
}