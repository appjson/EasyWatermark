package site.appjson.easywatermark.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.rosuh.cmonet.CMonet
import site.appjson.easywatermark.data.repo.MemorySettingRepo
import site.appjson.easywatermark.data.repo.WaterMarkRepository
import site.appjson.easywatermark.utils.ktx.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val waterMarkRepository: WaterMarkRepository,
    private val memorySettingRepo: MemorySettingRepo
) : ViewModel() {

    val waterMark = waterMarkRepository.waterMark.asLiveData()

    val palette = memorySettingRepo.paletteFlow.asLiveData()

    fun toggleBounds(enable: Boolean) {
        launch {
            waterMarkRepository.toggleBounds(enable)
        }
    }

    fun toggleSupportDynamicColor(enable: Boolean) {
        if (enable) {
            CMonet.forceSupportDynamicColor()
        } else {
            CMonet.disableSupportDynamicColor()
        }
    }
}
