package dev.techpolis.studservice.repositories.service.local

import dev.techpolis.studservice.data.database.ServiceDAO
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalServicesRepoImpl @Inject constructor(
    private val servicesDAO: ServiceDAO,
) : LocalServicesRepo {

    private val list: List<ServiceEntity> = listOf(
        ServiceEntity(
            title = "Вычмат",
            ownerId = "2",
            description = "Сделаю курсовую работу по вычику за 2 дня!",
            price = 1000,
            tagList = listOf(
                "Вычмат", "Учёба", "Политех"
            ),
            type = ServiceTypeEnum.OFFER,
            deadlineTime = 1623672788000
        ),
        ServiceEntity(
            title = "Волейбол",
            ownerId = "Sg0iaZxk5KSSwLDt7FrZuPYw5uj1",
            description = "Игра в волейбол около студ клуба",
            price = 0,
            tagList = listOf(
                "Волейбол", "Спорт", "Политех"
            ),
            type = ServiceTypeEnum.REQUEST,
            deadlineTime = 1623672788000
        ),
        ServiceEntity(
            title = "Пирожки",
            ownerId = "4",
            description = "Нажарю пирожков для всего этажа",
            price = 3000,
            tagList = listOf(
                "Еда", "Пирожки", "Общага 6"
            ),
            type = ServiceTypeEnum.OFFER,
            deadlineTime = 1623254400000
        ), ServiceEntity(
            title = "Спустить чемоданы",
            ownerId = "5",
            description = "Помогите спустить чемоданы с 5 этажа хрупкой девушке)",
            price = 0,
            tagList = listOf(
                "Помощь", "Поддержка", "Общежитие 14а"
            ),
            type = ServiceTypeEnum.REQUEST,
            deadlineTime = 1623700800000
        ), ServiceEntity(
            title = "Одолжу пылесос",
            ownerId = "5",
            description = "Одолжу пылесос за шоколадку)",
            price = 100,
            tagList = listOf(
                "6 общага"
            ),
            type = ServiceTypeEnum.OFFER,
            deadlineTime = 1639512000000
        ), ServiceEntity(
            title = "Мартыновские лабы",
            ownerId = "5",
            description = "Помогите сделать B часть лаб, с++",
            price = 4000,
            tagList = listOf(
                "Политех", "C++", "Мартынов"
            ),
            type = ServiceTypeEnum.REQUEST,
            deadlineTime = 1623355200000
        ), ServiceEntity(
            title = "ОПД",
            ownerId = "6",
            description = "Есть готовый проект, нужно просто изменить UI до неузнаваемости",
            price = 5000,
            tagList = listOf(
                "ОПД", "Android", "UI"
            ),
            type = ServiceTypeEnum.REQUEST,
            deadlineTime = 1623787200000
        )
        , ServiceEntity(
            title = "Фриланс-iOS",
            ownerId = "Sg0iaZxk5KSSwLDt7FrZuPYw5uj1",
            description = "Напишу любое приложение на iOS, цена договорная",
            price = 0,
            tagList = listOf(
                "Фриланс", "iOS", "Политех", "Общага 6", "Общага 14а"
            ),
            type = ServiceTypeEnum.OFFER,
            deadlineTime = 1640980800000
        )

    )

    init {
        GlobalScope.launch {
            if (servicesDAO.readAllService().first().isEmpty()) {
                servicesDAO.addServices(list)
            }
        }
    }

    override fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllService()
    }

    override fun readServicesByType(
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByType(type)
    }

    override fun readServicesByUser(
        userId: String,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByUserId(userId)
    }

    override fun readServicesByUserAndType(
        userId: String,
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByUserIdAndType(userId, type)
    }

    override suspend fun addService(service: ServiceEntity) {
        servicesDAO.addService(service)
    }

    override suspend fun addServices(services: List<ServiceEntity>) {
        servicesDAO.addServices(services)
    }

    override suspend fun deleteService(service: ServiceEntity) {
        servicesDAO.deleteService(service)
    }

    override suspend fun updateService(newService: ServiceEntity) {
        servicesDAO.updateService(newService)
    }
}