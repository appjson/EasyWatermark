package site.appjson.easywatermark.ui.panel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.slider.Slider
import site.appjson.easywatermark.data.model.WaterMark
import site.appjson.easywatermark.data.repo.WaterMarkRepository
import site.appjson.easywatermark.ui.base.BasePBFragment
import site.appjson.easywatermark.utils.ktx.commitWithAnimation

class VerticalPbFragment : BasePBFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.slideContentSize?.valueFrom = 0f
        binding?.slideContentSize?.valueTo = WaterMarkRepository.MAX_VERTICAL_GAP.toFloat()
    }

    override fun doOnChange(slider: Slider, value: Float, fromUser: Boolean) {
        shareViewModel.updateVertical(value.toInt())
    }

    override fun formatValue(config: WaterMark?): Float {
        return (config?.vGap?.toFloat() ?: 1f).coerceAtLeast(0f)
            .coerceAtMost(WaterMarkRepository.MAX_VERTICAL_GAP.toFloat())
    }

    override fun formatValueTips(config: WaterMark?): String {
        return "${config?.vGap ?: 1f}"
    }

    companion object {
        const val TAG = "VerticalPbFragment"

        fun replaceShow(fa: FragmentActivity, containerId: Int) {
            val f = fa.supportFragmentManager.findFragmentByTag(TAG)
            if (f?.isVisible == true) {
                return
            }
            fa.commitWithAnimation {
                replace(
                    containerId,
                    VerticalPbFragment(),
                    TAG
                )
            }
        }
    }
}
