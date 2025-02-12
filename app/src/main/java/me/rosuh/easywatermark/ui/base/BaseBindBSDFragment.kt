package site.appjson.easywatermark.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseBindBSDFragment<VB : ViewBinding> : BaseBSDFragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView(layoutInflater, container)
        return binding.root
    }

    abstract fun bindView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
