package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.adapters.ColorPickerAdapter
import com.app.nftcreatorappz.creator.converters.JsonConverter
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.creator.models.ColorPalette

fun CanvasActivity.extendedOnColorLongTapped(objId: Int, color: Int) {
    var it:List<ColorPalette> = AppData.colorPalettesDB.colorPalettesDao().getListColorPalettes()

    for (data in it) {
        if (data.objId == objId) {
            fromDB = data
            break
        }
    }

    val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
    newData.remove(color)
    AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(JsonConverter.convertListOfIntToJsonString(newData.toList()), fromDB!!.objId)
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        binding.colorPickerRecyclerViewActivityCanvas.adapter = ColorPickerAdapter(fromDB!!, this)
        binding.colorPickerRecyclerViewActivityCanvas.scrollToPosition(
            JsonConverter.convertJsonStringToListOfInt(
                fromDB!!.colorPaletteColorData
            ).size
        )
    }

}