package com.app.nftcreatorappz.creator.fragments.tools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.nftcreatorappz.creator.listeners.ToolsFragmentListener
import com.app.nftcreatorappz.databinding.ActionFragmentToolsBinding

class ToolsFragment : Fragment() {
    private fun setDefaultSelectedFAB() {
        currentlySelectedFAB = binding.btnPencilFragmentTools
    }

    companion object {
        fun newInstance() = ToolsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = ActionFragmentToolsBinding.inflate(inflater, container, false)

        setColorFor(binding.btnPencilFragmentTools)
        setDefaultSelectedFAB()
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

}