package dev.techpolis.studservice.views.main.services.offers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.base.BaseFragment
import dev.techpolis.studservice.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.views.auth.signin.SignInFragment
import dev.techpolis.studservice.views.auth.signin.SignInMvpView
import dev.techpolis.studservice.views.auth.signin.SignInPresenter
import javax.inject.Inject

class ServiceOffersFragment : BaseFragment() {

    private lateinit var presenter: ServiceOffersPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ServiceOffersMvpView = mvpViewFactory.createServiceOffersMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment = ServiceOffersFragment()
    }

}
