package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.data.StringConstants

fun CanvasActivity.getExtras() {
    spanCount = intent.getIntExtra(StringConstants.SPAN_COUNT_EXTRA, spanCount)
    index = intent.getIntExtra(StringConstants.INDEX_EXTRA, -1)
    title = (intent.getStringExtra(StringConstants.PROJECT_TITLE_EXTRA))
    projectTitle = title.toString()
}