package com.app.nftcreatorappz.creator.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View
import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.extensions.navigateTo
import com.app.nftcreatorappz.creator.fragments.tools.ToolsFragment
import com.app.nftcreatorappz.data.StringConstants


fun CanvasActivity.openColorPickerDialog(colorPaletteMode: Boolean = false) {
    colorPickerFragmentInstance = initColorPickerFragmentInstance(colorPaletteMode)
    currentFragmentInstance = colorPickerFragmentInstance
    navigateTo(
        supportFragmentManager,
        colorPickerFragmentInstance,
        R.id.activityCanvas_primaryFragmentHost,
        StringConstants.FRAGMENT_COLOR_PICKER_TITLE,
        binding.activityCanvasPrimaryFragmentHost,
        binding.rootLayoutActivityCanvas
    )
}

fun clearCanvas() {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.clearCanvas()
}

fun CanvasActivity.setOnClickListeners() {

    toolsFragmentInstance = ToolsFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .add(R.id.tabLayoutFragmentHostActivityCanvas, toolsFragmentInstance!!).commit()


    binding.colorPrimaryViewActivityCanvas.setOnLongClickListener {
        isPrimaryColorSelected = true
        openColorPickerDialog()
        hideMenuItems()
        true
    }

    binding.colorPrimaryViewActivityCanvas.setOnClickListener {
        isPrimaryColorSelected = true
        binding.colorPrimaryViewIndicatorActivityCanvas.visibility = View.VISIBLE
        setPixelColor((binding.colorPrimaryViewActivityCanvas.background as ColorDrawable).color)
    }
}