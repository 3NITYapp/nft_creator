package com.app.nftcreatorappz.creator.activities.canvas

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nftcreatorappz.creator.adapters.ColorPickerAdapter
import com.app.nftcreatorappz.creator.database.AppData

fun CanvasActivity.setUpRecyclerView() {
    val layoutManager = GridLayoutManager(this, 1)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
    binding.colorPickerRecyclerViewActivityCanvas.layoutManager = layoutManager

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        val toShow = if (fromDB != null) fromDB else it.firstOrNull()
        colorPickerAdapter = toShow?.let { obj ->
            selectedColorPalette = obj
            ColorPickerAdapter(obj, this)
        }
        binding.colorPickerRecyclerViewActivityCanvas.adapter = colorPickerAdapter
    }
}