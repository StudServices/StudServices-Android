package dev.techpolis.studservice.data

data class Resource<out T>(
    val status: Status,
    val data: T?,
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.Success, data)
        fun <T> error(): Resource<T> = Resource(Status.Error, null)
    }
}

sealed class Status {
    object Success : Status()
    object Error : Status()
}