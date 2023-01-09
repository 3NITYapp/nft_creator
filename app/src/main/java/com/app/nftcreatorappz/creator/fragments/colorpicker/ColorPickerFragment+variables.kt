package com.app.nftcreatorappz.creator.fragments.colorpicker

import android.graphics.Color
import com.app.nftcreatorappz.databinding.FragmentColorPickerBinding
import com.app.nftcreatorappz.creator.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.app.nftcreatorappz.creator.fragments.colorpicker.rgb.RGBColorPickerFragment
import com.app.nftcreatorappz.creator.listeners.ColorPickerFragmentListener

var binding_: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPaletteMode_: Boolean = false

val binding get() = binding_!!

lateinit var caller: ColorPickerFragmentListener

var rgbFragmentInstance: RGBColorPickerFragment? = null
var pickerFragmentInstance: ColorPickerPickerFragment? = null
