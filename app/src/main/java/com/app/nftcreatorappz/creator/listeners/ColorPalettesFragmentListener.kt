package com.app.nftcreatorappz.creator.listeners

import com.app.nftcreatorappz.creator.models.ColorPalette

interface ColorPalettesFragmentListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
}