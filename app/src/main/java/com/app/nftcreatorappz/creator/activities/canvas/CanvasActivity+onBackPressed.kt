package com.app.nftcreatorappz.creator.activities.canvas

import android.view.View
import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.extensions.navigateHome
import com.app.nftcreatorappz.creator.extensions.showDialog
import com.app.nftcreatorappz.data.StringConstants

fun CanvasActivity.extendedOnBackPressed() {
    if (!saved && currentFragmentInstance == null) {
        showDialog(
            this.getString(R.string.unsaved_changes),
            this.getString(R.string.have_unsaved),
            StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
            { _, _ ->
                finish()
            }, this.getString(R.string.cancel), { _, _ -> }, null)
    } else if (currentFragmentInstance != null) {
        navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.rootLayoutActivityCanvas, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
        currentFragmentInstance = null
        showMenuItems()

        if (isPrimaryColorSelected) {
            binding.colorPrimaryViewIndicatorActivityCanvas.visibility = View.VISIBLE
        } else {
            binding.colorPrimaryViewIndicatorActivityCanvas.visibility = View.INVISIBLE
        }
    } else {
        finish()
    }
}