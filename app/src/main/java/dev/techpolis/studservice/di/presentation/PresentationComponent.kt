package dev.techpolis.studservice.di.presentation

import dagger.Subcomponent
import dev.techpolis.studservice.views.auth.signin.SignInFragment
import dev.techpolis.studservice.views.auth.signup.SignUpFragment
import dev.techpolis.studservice.views.main.MainFragment
import dev.techpolis.studservice.views.main.filters.FiltersFragment
import dev.techpolis.studservice.views.main.map.MapFragment
import dev.techpolis.studservice.views.main.profile.ProfileFragment
import dev.techpolis.studservice.views.main.profile.settings.SettingsFragment
import dev.techpolis.studservice.views.main.serviceinfo.ServiceInfoFragment
import dev.techpolis.studservice.views.main.services.ServicesFragment
import dev.techpolis.studservice.views.main.services.offers.ServiceOffersFragment
import dev.techpolis.studservice.views.main.services.requests.ServiceRequestsFragment
import dev.techpolis.studservice.views.main.userservices.UserServicesFragment
import dev.techpolis.studservice.views.main.userservices.newservice.NewServiceFragment
import dev.techpolis.studservice.views.main.userservices.offers.UserServiceOffersFragment
import dev.techpolis.studservice.views.main.userservices.requests.UserServiceRequestsFragment

@Subcomponent(
    modules = []
) @PresentationScope interface PresentationComponent {

    fun inject(signInFragment: SignInFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(servicesFragment: ServicesFragment)
    fun inject(userServicesFragment: UserServicesFragment)
    fun inject(newServiceFragment: NewServiceFragment)
//    fun inject(serviceRequestFragment: ServiceRequestsFragment)
    fun inject(serviceOffersFragment: ServiceOffersFragment)
    fun inject(userServiceOffersFragment: UserServiceOffersFragment)
    fun inject(userServicesRequestsFragment: UserServiceRequestsFragment)
    fun inject(servicesInfoFragment: ServiceInfoFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(filtersFragment: FiltersFragment)
    fun inject(mapFragment: MapFragment)
    fun inject(mainFragment: MainFragment)
}