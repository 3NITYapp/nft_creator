package com.app.nftcreatorappz.creator.activities.canvas

import android.graphics.Color
import android.view.View
import com.app.nftcreatorappz.creator.adapters.ColorPickerAdapter
import com.app.nftcreatorappz.creator.converters.JsonConverter
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.creator.extensions.navigateHome
import com.app.nftcreatorappz.data.StringConstants

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {
    showMenuItems()
    setPixelColor(selectedColor)
    val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
    if(!newData.contains(selectedColor)){
        newData.add(selectedColor)
        newData.removeIf { it == Color.TRANSPARENT }
        newData.add(Color.TRANSPARENT)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(JsonConverter.convertListOfIntToJsonString(newData.toList()), fromDB!!.objId)
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
            binding.colorPickerRecyclerViewActivityCanvas.adapter =
                ColorPickerAdapter(fromDB!!, this)
            binding.colorPickerRecyclerViewActivityCanvas.scrollToPosition(
                JsonConverter.convertJsonStringToListOfInt(
                    fromDB!!.colorPaletteColorData
                ).size
            )
        }
    }

    currentFragmentInstance = null
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayoutActivityCanvas, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(
        StringConstants.PROJECT_TITLE_EXTRA)!!)

    if (isPrimaryColorSelected) {
        binding.colorPrimaryViewIndicatorActivityCanvas.visibility = View.VISIBLE
    } else {
        binding.colorPrimaryViewIndicatorActivityCanvas.visibility = View.INVISIBLE
    }
}