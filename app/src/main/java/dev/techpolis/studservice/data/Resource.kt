package dev.techpolis.studservice.data

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: Throwable?,
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.Success, data, null)
        fun <T> error(error: Throwable? = null): Resource<T> = Resource(Status.Error, null, error)
    }
}

sealed class Status {
    object Success : Status()
    object Error : Status()
}