package com.example.ihc_1.ui.copyactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CopyFragmentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Insert a text to be sent to the next screen"
    }
    val text: LiveData<String> = _text
}