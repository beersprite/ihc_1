package com.example.ihc_1.ui.sumactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ihc_1.R

class SumActivityViewModel : ViewModel() {

    private val _description_text = MutableLiveData<String>().apply {
        value = "Add two numbers"
    }
    private val _result_sum_text = MutableLiveData<String>().apply {
        value = "Result is "
    }
    val description_text: LiveData<String> = _description_text
    val result_sum_text: LiveData<String> = _result_sum_text
}