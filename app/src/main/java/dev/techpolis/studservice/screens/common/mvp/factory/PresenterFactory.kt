package dev.techpolis.studservice.screens.common.mvp.factory

import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.interactors.AuthInteractor
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceInfoProvider
import dev.techpolis.studservice.screens.auth.signin.SignInPresenter
import dev.techpolis.studservice.screens.auth.signup.SignUpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.screens.main.MainPresenter
import dev.techpolis.studservice.screens.main.map.MapPresenter
import dev.techpolis.studservice.screens.main.profile.ProfilePresenter
import dev.techpolis.studservice.screens.main.profile.settings.SettingsPresenter
import dev.techpolis.studservice.screens.main.search.SearchPresenter
import dev.techpolis.studservice.screens.main.serviceinfo.ServiceInfoPresenter
import dev.techpolis.studservice.screens.main.services.ServicesPresenter
import dev.techpolis.studservice.screens.main.services.offers.ServiceOffersPresenter
import dev.techpolis.studservice.screens.main.services.requests.ServiceRequestsPresenter
import dev.techpolis.studservice.screens.main.userservices.UserServicesPresenter
import dev.techpolis.studservice.screens.main.userservices.newservice.NewServicePresenter
import dev.techpolis.studservice.screens.main.userservices.offers.UserServiceOffersPresenter
import dev.techpolis.studservice.screens.main.userservices.requests.UserServiceRequestsPresenter
import javax.inject.Inject

@ActivityScope
class PresenterFactory @Inject constructor(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val serviceInteractor: ServiceInteractor,
    private val authInteractor: AuthInteractor,
    private val serviceInfoProvider: ServiceInfoProvider,
) {
    lateinit var mainScreenRouter: MainScreenRouter

    fun createSignInPresenter(): SignInPresenter =
        SignInPresenter(appScreenRouter, backPressDispatcher, authInteractor)

    fun createSignUpPresenter(): SignUpPresenter =
        SignUpPresenter(appScreenRouter, backPressDispatcher, authInteractor)

    fun createMainPresenter(): MainPresenter =
        MainPresenter(mainScreenRouter, backPressDispatcher)

    fun createProfilePresenter(): ProfilePresenter {
        return ProfilePresenter(appScreenRouter, mainScreenRouter, backPressDispatcher)
    }

    fun createServiceRequestPresenter(): ServiceRequestsPresenter {
        return ServiceRequestsPresenter(
            serviceInteractor,
            serviceInfoProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createServiceOffersPresenter(): ServiceOffersPresenter {
        return ServiceOffersPresenter(
            serviceInteractor,
            serviceInfoProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createServicesPresenter(): ServicesPresenter {
        return ServicesPresenter(backPressDispatcher, mainScreenRouter)
    }

    fun createUserServicesPresenter(): UserServicesPresenter {
        return UserServicesPresenter(backPressDispatcher, mainScreenRouter)
    }

    fun createUserServiceOffersPresenter(): UserServiceOffersPresenter {
        return UserServiceOffersPresenter(
            serviceInteractor,
            serviceInfoProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createUserServiceRequestsPresenter(): UserServiceRequestsPresenter {
        return UserServiceRequestsPresenter(
            serviceInteractor,
            serviceInfoProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createNewServicePresenter(): NewServicePresenter {
        return NewServicePresenter(mainScreenRouter, backPressDispatcher)
    }

    fun createServiceInfoPresenter(): ServiceInfoPresenter {
        return ServiceInfoPresenter(serviceInfoProvider, backPressDispatcher, mainScreenRouter)
    }

    fun createSettingsPresenter(): SettingsPresenter {
        return SettingsPresenter(mainScreenRouter, backPressDispatcher)
    }

    fun createMapPresenter(): MapPresenter {
        return MapPresenter()
    }

    fun createSearchPresenter(): SearchPresenter {
        return SearchPresenter(mainScreenRouter, backPressDispatcher)
    }

}
