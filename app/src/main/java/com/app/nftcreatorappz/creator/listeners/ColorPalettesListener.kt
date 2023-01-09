package com.app.nftcreatorappz.creator.listeners

import com.app.nftcreatorappz.creator.models.ColorPalette

interface ColorPalettesListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
    fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette)
}