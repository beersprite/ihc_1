package com.example.ihc_1.ui.sumactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ihc_1.databinding.FragmentSumActivityBinding


class SumActivityFragment : Fragment() {

    private var _binding: FragmentSumActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var sumResult: Number

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val sumActivityViewModel =
            ViewModelProvider(this).get(SumActivityViewModel::class.java)

        _binding = FragmentSumActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val descriptionText: TextView = binding.descriptionText
        sumActivityViewModel.description_text.observe(viewLifecycleOwner) {
            descriptionText.text = it
        }
        val resultSumText: TextView = binding.resultSumText
        sumActivityViewModel.result_sum_text.observe(viewLifecycleOwner) {
            resultSumText.text = it
        }

        binding.sumButton.setOnClickListener{
            val firstNumber = binding.editTextNumberSigned.text.toString()
            val secondNumber = binding.editTextNumberSigned2.text.toString()

            if (firstNumber != "" && secondNumber != "" ){
                sumResult = firstNumber.toInt() + secondNumber.toInt()
                resultSumText.text = "Result is $sumResult"

            }
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}