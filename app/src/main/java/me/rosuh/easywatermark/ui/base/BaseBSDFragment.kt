package site.appjson.easywatermark.ui.base

import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import site.appjson.easywatermark.data.model.WaterMark
import site.appjson.easywatermark.ui.MainViewModel

open class BaseBSDFragment : BottomSheetDialogFragment() {

    protected val shareViewModel: MainViewModel by activityViewModels()

    protected val config: WaterMark?
        get() {
            return shareViewModel.waterMark.value
        }
}
