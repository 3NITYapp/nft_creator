package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.adapters.ColorPickerAdapter
import com.app.nftcreatorappz.creator.models.ColorPalette

fun CanvasActivity.extendedOnColorPaletteTapped(selectedColorPalette: ColorPalette) {
    binding.colorPickerRecyclerViewActivityCanvas.adapter = ColorPickerAdapter(selectedColorPalette, this)
}