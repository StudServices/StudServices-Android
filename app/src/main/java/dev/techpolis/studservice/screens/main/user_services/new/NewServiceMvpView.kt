package dev.techpolis.studservice.screens.main.user_services.new

import dev.techpolis.studservice.data.model.DeadlineDate
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface NewServiceMvpView : MvpViewObservable<NewServiceMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onCreateServiceBtnClicked(
            title: String,
            description: String,
//            serviceType: ServiceTypeEnum,
            price: Int,
//            deadline: Long,
//            tags: List<String>
        )

        fun onBackBtnClicked()
        fun onChipAdded(name: String)
        fun onChipDeleted(name: String)
        fun getToDatePicker()
        fun onTypeSelected(type: ServiceTypeEnum)
    }

    fun setDate(deadline: DeadlineDate)
    fun setTagList(tagList: List<String>)
    fun setTypeTab(type: ServiceTypeEnum)

}