package site.appjson.easywatermark.data.model

import android.net.Uri
import androidx.annotation.Keep
import site.appjson.easywatermark.data.repo.WaterMarkRepository

@Keep
data class WaterMark(
    val text: String,
    val textSize: Float,
    val textColor: Int,
    val textStyle: TextPaintStyle,
    val textTypeface: TextTypeface,
    val alpha: Int,
    val degree: Float,
    val hGap: Int,
    val vGap: Int,
    val iconUri: Uri,
    val markMode: WaterMarkRepository.MarkMode,
    val enableBounds: Boolean
)
