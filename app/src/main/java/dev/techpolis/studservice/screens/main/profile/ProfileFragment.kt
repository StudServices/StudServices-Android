package dev.techpolis.studservice.screens.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.screens.common.base.BaseFragment

class ProfileFragment : BaseFragment() {

    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createProfilePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ProfileMvpView = mvpViewFactory.createProfileMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment = ProfileFragment()
    }

}
