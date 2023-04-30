package site.appjson.easywatermark.ui.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import site.appjson.easywatermark.databinding.FragmentTextStyleBinding
import site.appjson.easywatermark.ui.adapter.DividerAdapter
import site.appjson.easywatermark.ui.adapter.TextPaintStyleAdapter
import site.appjson.easywatermark.ui.adapter.TextTypefaceAdapter
import site.appjson.easywatermark.ui.base.BaseBindFragment
import site.appjson.easywatermark.ui.widget.utils.BounceEdgeEffectFactory
import site.appjson.easywatermark.utils.ktx.commitWithAnimation

class TextStyleFragment : BaseBindFragment<FragmentTextStyleBinding>() {
    override fun bindView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTextStyleBinding {
        return FragmentTextStyleBinding.inflate(layoutInflater)
    }

    private val paintStyleAdapter by lazy {
        TextPaintStyleAdapter(
            TextPaintStyleAdapter.obtainDefaultPaintStyleList(
                requireContext()
            ),
            shareViewModel.waterMark.value?.textStyle
        ) { _, paintStyle ->
            shareViewModel.updateTextStyle(paintStyle)
            typefaceAdapter.updateTextStyle(paintStyle)
        }
    }
    private val typefaceAdapter by lazy {
        TextTypefaceAdapter(
            TextTypefaceAdapter.obtainDefaultTypefaceList(
                requireContext()
            ),
            shareViewModel.waterMark.value?.textTypeface
        ) { _, typeface ->
            shareViewModel.updateTextTypeface(typeface)
        }
    }

    private val concatAdapter by lazy {
        ConcatAdapter(
            paintStyleAdapter,
            DividerAdapter(),
            typefaceAdapter,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvColor?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = concatAdapter
            edgeEffectFactory = BounceEdgeEffectFactory(context, this)
        }
    }

    companion object {
        const val TAG = "TextStyleFragment"

        fun replaceShow(fa: FragmentActivity, containerId: Int) {
            val f = fa.supportFragmentManager.findFragmentByTag(TAG)
            if (f?.isVisible == true) {
                return
            }
            fa.commitWithAnimation {
                replace(
                    containerId,
                    TextStyleFragment(),
                    TAG
                )
            }
        }
    }
}
