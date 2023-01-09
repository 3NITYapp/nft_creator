package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.converters.BitmapConverter
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.creator.utility.IntConstants

fun CanvasActivity.replaceBitmapIfApplicable() {
    if (index != -1) {
        AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations("").observe(context) {
            currentPixelArtObj = it[index!!]
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!)
            outerCanvasInstance.rotate(it[index!!].rotation.toInt(), false)
            IntConstants.SPAN_COUNT = currentPixelArtObj.width
        }
    }
}