package com.example.ihc_1.ui.copyactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ihc_1.databinding.FragmentCopyActivityBinding

class CopyFragment : Fragment() {

    private var _binding: FragmentCopyActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val copyViewModel =
                ViewModelProvider(this).get(CopyFragmentViewModel::class.java)

        _binding = FragmentCopyActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        copyViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.sendButton.setOnClickListener {
            this.sendText(requireContext())
        }

        return root
    }

    private fun sendText(context: Context){
        val intent = Intent(context, PasteActivity::class.java)

        var currentText = binding.inputTextCopy.text.toString()
        intent.putExtra("sent_text_id", currentText)

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}