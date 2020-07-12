package com.ekezet.base.api

/**
 * @author kiri
 */
sealed class ApiResponse<T> constructor(
    open val data: T?,
    open val throwable: Throwable?
) {
    class LoadingResponse<T> : ApiResponse<T>(null, null)
    class SuccessResponse<T>(override val data: T) : ApiResponse<T>(data, null)
    class FailureResponse<T>(override val throwable: Throwable) :
        ApiResponse<T>(null, throwable)

    companion object {
        fun <T> loading(): LoadingResponse<T> = LoadingResponse()
        fun <T> success(data: T): SuccessResponse<T> = SuccessResponse(data)
        fun <T> failure(throwable: Throwable): FailureResponse<T> = FailureResponse(throwable)
    }
}
