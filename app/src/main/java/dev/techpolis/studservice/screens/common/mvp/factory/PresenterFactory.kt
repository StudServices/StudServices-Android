package dev.techpolis.studservice.screens.common.mvp.factory

import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceProvider
import dev.techpolis.studservice.screens.auth.signin.SignInPresenter
import dev.techpolis.studservice.screens.auth.signup.SignUpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.screens.main.MainPresenter
import dev.techpolis.studservice.screens.main.filters.FiltersPresenter
import dev.techpolis.studservice.screens.main.map.MapPresenter
import dev.techpolis.studservice.screens.main.profile.ProfilePresenter
import dev.techpolis.studservice.screens.main.profile.settings.SettingsPresenter
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
    private val serviceProvider: ServiceProvider,
) {
    lateinit var mainScreenRouter: MainScreenRouter

    fun createSignInPresenter(): SignInPresenter =
        SignInPresenter(appScreenRouter, backPressDispatcher)

    fun createSignUpPresenter(): SignUpPresenter =
        SignUpPresenter(appScreenRouter, backPressDispatcher)

    fun createMainPresenter(): MainPresenter =
        MainPresenter(mainScreenRouter, backPressDispatcher)

    fun createProfilePresenter(): ProfilePresenter {
        return ProfilePresenter(mainScreenRouter, backPressDispatcher)
    }

    fun createServiceRequestPresenter(): ServiceRequestsPresenter {
        return ServiceRequestsPresenter(
            serviceInteractor,
            serviceProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createServiceOffersPresenter(): ServiceOffersPresenter {
        return ServiceOffersPresenter(
            serviceInteractor,
            serviceProvider,
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
            serviceProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createUserServiceRequestsPresenter(): UserServiceRequestsPresenter {
        return UserServiceRequestsPresenter(
            serviceInteractor,
            serviceProvider,
            mainScreenRouter,
            backPressDispatcher
        )
    }

    fun createNewServicePresenter(): NewServicePresenter {
        return NewServicePresenter()
    }

    fun createServiceInfoPresenter(): ServiceInfoPresenter {
        return ServiceInfoPresenter(serviceProvider, backPressDispatcher, mainScreenRouter)
    }

    fun createSettingsPresenter(): SettingsPresenter {
        return SettingsPresenter()
    }

    fun createMapPresenter(): MapPresenter {
        return MapPresenter()
    }

    fun createFilterPresenter(): FiltersPresenter {
        return FiltersPresenter()
    }

}