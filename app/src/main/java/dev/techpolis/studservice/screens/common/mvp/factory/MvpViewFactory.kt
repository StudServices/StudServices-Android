package dev.techpolis.studservice.screens.common.mvp.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.screens.auth.signin.SignInMvpView
import dev.techpolis.studservice.screens.auth.signin.SignInMvpViewImpl
import dev.techpolis.studservice.screens.auth.signup.SignUpMvpView
import dev.techpolis.studservice.screens.auth.signup.SignUpMvpViewImpl
import dev.techpolis.studservice.screens.main.MainMvpView
import dev.techpolis.studservice.screens.main.MainMvpViewImpl
import dev.techpolis.studservice.screens.main.filters.FiltersMvpView
import dev.techpolis.studservice.screens.main.filters.FiltersMvpViewImpl
import dev.techpolis.studservice.screens.main.map.MapMvpView
import dev.techpolis.studservice.screens.main.map.MapMvpViewImpl
import dev.techpolis.studservice.screens.main.profile.ProfileMvpView
import dev.techpolis.studservice.screens.main.profile.ProfileMvpViewImpl
import dev.techpolis.studservice.screens.main.profile.settings.SettingsMvpView
import dev.techpolis.studservice.screens.main.profile.settings.SettingsMvpViewImpl
import dev.techpolis.studservice.screens.main.serviceinfo.ServiceInfoMvpView
import dev.techpolis.studservice.screens.main.serviceinfo.ServiceInfoMvpViewImpl
import dev.techpolis.studservice.screens.main.services.ServicesAdapter
import dev.techpolis.studservice.screens.main.services.ServicesMvpView
import dev.techpolis.studservice.screens.main.services.ServicesMvpViewImpl
import dev.techpolis.studservice.screens.main.services_item.ServicesItemMvpView
import dev.techpolis.studservice.screens.main.services_item.ServicesItemMvpViewImpl
import dev.techpolis.studservice.screens.main.services.offers.ServiceOffersMvpView
import dev.techpolis.studservice.screens.main.services.offers.ServiceOffersMvpViewImpl
import dev.techpolis.studservice.screens.main.services.requests.ServiceRequestsMvpView
import dev.techpolis.studservice.screens.main.services.requests.ServiceRequestsMvpViewImpl
import dev.techpolis.studservice.screens.main.userservices.UserServicesMvpView
import dev.techpolis.studservice.screens.main.userservices.UserServicesMvpViewImpl
import dev.techpolis.studservice.screens.main.userservices.newservice.NewServiceMvpView
import dev.techpolis.studservice.screens.main.userservices.newservice.NewServiceMvpViewImpl
import dev.techpolis.studservice.screens.main.userservices.offers.UserServiceOffersMvpView
import dev.techpolis.studservice.screens.main.userservices.offers.UserServiceOffersMvpViewImpl
import dev.techpolis.studservice.screens.main.userservices.requests.UserServiceRequestsMvpView
import dev.techpolis.studservice.screens.main.userservices.requests.UserServiceRequestsMvpViewImpl
import javax.inject.Inject

@ActivityScope
class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
) {
    fun createSignInMvpView(parent: ViewGroup?): SignInMvpView =
        SignInMvpViewImpl(layoutInflater, parent)

    fun createSignUpMvpView(parent: ViewGroup?): SignUpMvpView =
        SignUpMvpViewImpl(layoutInflater, parent)

    fun createServiceOffersMvpView(parent: ViewGroup?): ServiceOffersMvpView =
        ServiceOffersMvpViewImpl(layoutInflater, parent, this)

    fun createServiceRequestsMvpView(parent: ViewGroup?): ServiceRequestsMvpView =
        ServiceRequestsMvpViewImpl(layoutInflater, parent, this)

    fun createUserServiceOffersMvpView(parent: ViewGroup?): UserServiceOffersMvpView =
        UserServiceOffersMvpViewImpl(layoutInflater, parent, this)

    fun createUserServiceRequestsMvpView(parent: ViewGroup?): UserServiceRequestsMvpView =
        UserServiceRequestsMvpViewImpl(layoutInflater, parent, this)

    fun createNewServiceMvpView(parent: ViewGroup?): NewServiceMvpView =
        NewServiceMvpViewImpl(layoutInflater, parent)

    fun createSettingsMvpView(parent: ViewGroup?): SettingsMvpView =
        SettingsMvpViewImpl(layoutInflater, parent)

    fun createProfileMvpView(parent: ViewGroup?): ProfileMvpView =
        ProfileMvpViewImpl(layoutInflater, parent)

    fun createServiceInfoMvpView(parent: ViewGroup?): ServiceInfoMvpView =
        ServiceInfoMvpViewImpl(layoutInflater, parent)

    fun createFiltersMvpView(parent: ViewGroup?): FiltersMvpView =
        FiltersMvpViewImpl(layoutInflater, parent)

    fun createMapMvpView(parent: ViewGroup?): MapMvpView =
        MapMvpViewImpl(layoutInflater, parent)

    fun createServicesMvpView(parent: ViewGroup?, fragment: Fragment): ServicesMvpView =
        ServicesMvpViewImpl(layoutInflater, parent, fragment)

    fun createUserServicesMvpView(parent: ViewGroup?, fragment: Fragment): UserServicesMvpView =
        UserServicesMvpViewImpl(layoutInflater, parent, fragment)

    fun createMainMvpView(parent: ViewGroup?): MainMvpView =
        MainMvpViewImpl(layoutInflater, parent)

    fun createServicesAdapter(
        listener: ServicesItemMvpView.Listener,
        glide: RequestManager
    ): ServicesAdapter =
        ServicesAdapter(listener, this, glide)

    fun createServiceItemMvpView(
        parent: ViewGroup,
        listener: ServicesItemMvpView.Listener,
        glide: RequestManager
    ): ServicesItemMvpView {
        return ServicesItemMvpViewImpl(layoutInflater, parent, listener, glide)
    }

}