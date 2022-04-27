package com.jtorreal.superheromarvel.data.network


sealed class Result<T>(
    body: T? = null,
    error: String? = null
) {
    data class Success<T>(val body: T) : Result<T>(body = body)

    data class Error<T>(val errorMessage: String? = null, val body: T? = null) :
        Result<T>(error = errorMessage, body = body)

}

