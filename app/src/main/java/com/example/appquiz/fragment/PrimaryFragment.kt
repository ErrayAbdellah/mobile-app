package com.example.appquiz.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.appquiz.R
import com.example.appquiz.databinding.FragmentPrimaryBinding

class PrimaryFragment : Fragment() {

    private var _binding: FragmentPrimaryBinding? = null
    private val binding get() = _binding!!
    private var listener: OnPrimaryFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPrimaryFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnPrimaryFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrimaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up button listeners
        binding.startBtnId.setOnClickListener {
            listener?.onStartClicked()
        }
        binding.resetScoreBtnId.setOnClickListener {
            listener?.onResetClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }

    interface OnPrimaryFragmentInteractionListener {
        fun onStartClicked()
        fun onResetClicked()
    }
}