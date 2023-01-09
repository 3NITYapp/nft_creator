package com.app.nftcreatorappz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.nftcreatorappz.ActivityIntro
import com.app.nftcreatorappz.creator.utility.FileHelperUtilities
import com.app.nftcreatorappz.data.SharedPref
import com.app.nftcreatorappz.databinding.FragmentSettingBinding
import com.app.nftcreatorappz.utils.Tools

class FragmentSetting : Fragment() {

    lateinit var binding: FragmentSettingBinding
    lateinit var sharedPref: SharedPref

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        sharedPref = SharedPref(requireContext())
        return binding.root
    }

    companion object {
        @JvmStatic
        fun instance() = FragmentSetting()
    }

    override fun onResume() {
        super.onResume()
        initComponent()
    }

    private fun initComponent() {
        binding.include.folderLocation.text = FileHelperUtilities(requireContext()).getDirectory()?.absolutePath
        binding.include.buildVersion.text = Tools.getVersionName(requireContext())

        binding.include.lytFolderLocation.setOnClickListener {
            Tools.openFolder(requireContext())
        }
        binding.include.lytIntroduction.setOnClickListener {
            ActivityIntro().navigate(requireActivity())
        }
        binding.include.lytAbout.setOnClickListener {
            Tools().showDialogProject(requireActivity())
        }
    }
}