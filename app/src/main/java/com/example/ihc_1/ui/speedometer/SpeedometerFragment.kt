package com.example.ihc_1.ui.speedometer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ihc_1.R

class SpeedometerFragment : Fragment() {

    companion object {
        fun newInstance() = SpeedometerFragment()
    }

    private lateinit var viewModel: SpeedometerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speedometer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpeedometerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}