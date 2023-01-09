package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.algorithms.AlgorithmInfoParameter
import com.app.nftcreatorappz.creator.algorithms.PixelPerfectAlgorithm

fun CanvasActivity.extendedOnActionUp() {
    if (currentTool == Tools.LINE_TOOL) {
        lineOrigin = null
        lineMode_hasLetGo = true
        rectangleMode_hasLetGo = true
    } else if (currentTool == Tools.RECTANGLE_TOOL || currentTool == Tools.OUTLINED_RECTANGLE_TOOL) {
        rectangleOrigin = null
        rectangleMode_hasLetGo = true
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)

        if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
            && (currentTool == Tools.PENCIL_TOOL)) {
            val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(AlgorithmInfoParameter(
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                getSelectedColor(),
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData))
            pixelPerfectAlgorithmInstance.compute()
        }

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewX = null
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewY = null
    }
}