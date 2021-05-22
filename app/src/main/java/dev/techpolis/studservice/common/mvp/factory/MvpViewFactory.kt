package dev.techpolis.studservice.common.mvp.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.techpolis.studservice.views.auth.signin.SignInMvpView
import dev.techpolis.studservice.views.auth.signin.SignInMvpViewImpl
import dev.techpolis.studservice.views.auth.signup.SignUpMvpView
import dev.techpolis.studservice.views.auth.signup.SignUpMvpViewImpl
import dev.techpolis.studservice.views.main.services.offers.ServiceOffersMvpView
import dev.techpolis.studservice.views.main.services.offers.ServiceOffersMvpViewImpl
import dev.techpolis.studservice.views.main.services.requests.ServiceRequestsMvpView
import dev.techpolis.studservice.views.main.services.requests.ServiceRequestsMvpViewImpl
import dev.techpolis.studservice.views.main.userservices.newservice.NewServiceMvpView
import dev.techpolis.studservice.views.main.userservices.newservice.NewServiceMvpViewImpl
import dev.techpolis.studservice.views.main.userservices.offers.UserServiceOffersMvpView
import dev.techpolis.studservice.views.main.userservices.offers.UserServiceOffersMvpViewImpl
import dev.techpolis.studservice.views.main.userservices.requests.UserServiceRequestsMvpView
import dev.techpolis.studservice.views.main.userservices.requests.UserServiceRequestsMvpViewImpl
import javax.inject.Inject

class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
) {
    fun createSignInMvpView(parent: ViewGroup?): SignInMvpView =
        SignInMvpViewImpl(layoutInflater, parent)

    fun createSignUpMvpView(parent: ViewGroup?): SignUpMvpView =
        SignUpMvpViewImpl(layoutInflater, parent)

    fun createServiceOffersMvpView(parent: ViewGroup?): ServiceOffersMvpView =
        ServiceOffersMvpViewImpl(layoutInflater, parent)

    fun createServiceRequestsMvpView(parent: ViewGroup?): ServiceRequestsMvpView =
        ServiceRequestsMvpViewImpl(layoutInflater, parent)

    fun createUserServiceOffersMvpView(parent: ViewGroup?): UserServiceOffersMvpView =
        UserServiceOffersMvpViewImpl(layoutInflater, parent)

    fun createUserServiceRequestsMvpView(parent: ViewGroup?): UserServiceRequestsMvpView =
        UserServiceRequestsMvpViewImpl(layoutInflater, parent)

    fun createNewServiceMvpView(parent: ViewGroup?): NewServiceMvpView =
        NewServiceMvpViewImpl(layoutInflater, parent)

}