package com.app.nftcreatorappz.creator.listeners

import android.view.View
import com.app.nftcreatorappz.creator.models.ColorPalette

interface ColorPickerListener {
    fun onColorTapped(colorTapped: Int, view: View)
    fun onColorLongTapped(paletteId: Int, colorTapped: Int, view: View)
    fun onColorAdded(colorPalette: ColorPalette)
}