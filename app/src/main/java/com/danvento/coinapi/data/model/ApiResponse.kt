package com.danvento.coinapi.data.model

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val httpCode: Int? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(message: String?, data: T? = null, httpCode: Int? = null) : ApiResponse<T>(data, message)
}
