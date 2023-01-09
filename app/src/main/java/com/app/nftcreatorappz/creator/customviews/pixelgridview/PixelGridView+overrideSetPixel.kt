package com.app.nftcreatorappz.creator.customviews.pixelgridview

import com.app.nftcreatorappz.creator.activities.canvas.outerCanvasInstance
import com.app.nftcreatorappz.creator.models.BitmapActionData
import com.app.nftcreatorappz.creator.models.Coordinates

fun PixelGridView.extendedOverrideSetPixel(x: Int, y: Int, color: Int) {
    val coordinates = Coordinates(x, y)
    if (coordinatesInCanvasBounds(coordinates)) {
        pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y)))
    }
}