package site.appjson.easywatermark.ui.panel

import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import site.appjson.easywatermark.R
import site.appjson.easywatermark.databinding.FragmentTileModeBinding
import site.appjson.easywatermark.ui.base.BaseBindFragment
import site.appjson.easywatermark.utils.ktx.commitWithAnimation
import site.appjson.easywatermark.utils.ktx.titleTextColor

class TileModeFragment : BaseBindFragment<FragmentTileModeBinding>() {

    override fun bindView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTileModeBinding {
        return FragmentTileModeBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareViewModel.selectedImage.observe(viewLifecycleOwner) {
            if (it == null) {
                return@observe
            }
            val checkedId = when (it.tileMode) {
                Shader.TileMode.CLAMP.ordinal -> R.id.rb_tile_mode_decal
                else -> R.id.rb_tile_mode_repeat
            }
            binding?.rgTileMode?.setOnCheckedChangeListener(null)
            binding?.rgTileMode?.check(checkedId)
            binding?.rgTileMode?.setOnCheckedChangeListener { _, id ->
                val imageInfo = it
                if (id == R.id.rb_tile_mode_decal && imageInfo.tileMode == Shader.TileMode.CLAMP.ordinal) {
                    return@setOnCheckedChangeListener
                }
                if (id == R.id.rb_tile_mode_repeat && imageInfo.tileMode == Shader.TileMode.REPEAT.ordinal) {
                    return@setOnCheckedChangeListener
                }
                when(id) {
                    R.id.rb_tile_mode_decal -> shareViewModel.updateTileMode(imageInfo, Shader.TileMode.CLAMP)
                    else -> shareViewModel.updateTileMode(imageInfo, Shader.TileMode.REPEAT)
                }
            }
        }
        shareViewModel.colorPalette.observe(this.viewLifecycleOwner) {
            val color = it.titleTextColor(requireContext())
            binding?.rbTileModeDecal?.setTextColor(color)
            binding?.rbTileModeRepeat?.setTextColor(color)
        }
    }

    companion object {
        const val TAG = "TileModeFragment"

        fun replaceShow(fa: FragmentActivity, containerId: Int) {
            val f = fa.supportFragmentManager.findFragmentByTag(TAG)
            if (f?.isVisible == true) {
                return
            }
            fa.commitWithAnimation {
                replace(
                    containerId,
                    TileModeFragment(),
                    TAG
                )
            }
        }
    }
}
