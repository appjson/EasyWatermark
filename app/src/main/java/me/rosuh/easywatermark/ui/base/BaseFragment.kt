package site.appjson.easywatermark.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import site.appjson.easywatermark.data.model.WaterMark
import site.appjson.easywatermark.ui.MainViewModel

open class BaseFragment : Fragment() {

    protected val shareViewModel: MainViewModel by activityViewModels()

    protected val config: WaterMark?
        get() {
            return shareViewModel.waterMark.value
        }

}
