package site.appjson.easywatermark.ui.panel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.slider.Slider
import site.appjson.easywatermark.data.model.WaterMark
import site.appjson.easywatermark.data.repo.WaterMarkRepository
import site.appjson.easywatermark.ui.base.BasePBFragment
import site.appjson.easywatermark.utils.ktx.commitWithAnimation

class TextSizePbFragment : BasePBFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.slideContentSize?.valueFrom = 1f
        binding?.slideContentSize?.valueTo = WaterMarkRepository.MAX_TEXT_SIZE
    }

    override fun doOnChange(slider: Slider, value: Float, fromUser: Boolean) {
        shareViewModel.updateTextSize(value)
    }

    override fun formatValue(config: WaterMark?): Float {
        return (config?.textSize?.toInt()?.toFloat() ?: 1f).coerceAtLeast(1f).coerceAtMost(WaterMarkRepository.MAX_TEXT_SIZE)
    }

    override fun formatValueTips(config: WaterMark?): String {
        val size = (config?.textSize?.toInt()?.toFloat() ?: 1f).coerceAtLeast(1f).coerceAtMost(WaterMarkRepository.MAX_TEXT_SIZE)
        return "$size"
    }

    companion object {
        const val TAG = "TextSizePbFragment"

        fun replaceShow(fa: FragmentActivity, containerId: Int) {
            val f = fa.supportFragmentManager.findFragmentByTag(TAG)
            if (f?.isVisible == true) {
                return
            }
            fa.commitWithAnimation {
                replace(
                    containerId,
                    TextSizePbFragment(),
                    TAG
                )
            }
        }
    }
}
