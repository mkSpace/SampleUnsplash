package com.mashup.sampleharrypotter.data

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.haroldadmin.cnradapter.NetworkResponse

data class ErrorResponse(val error: Error? = null) {
    data class Error(
        @SerializedName("code") val errorCode: String? = null,
        val message: String? = null
    )
}

typealias NetworkResponse<T> = NetworkResponse<T, ErrorResponse>

fun <T : Any> NetworkResponse<T, ErrorResponse>.onErrorReturnNull(): T? = when (this) {
    is NetworkResponse.Success -> body
    is NetworkResponse.ServerError -> {
        Log.e(
            "NetworkResponse",
            "onErrorReturn: ServerError(${this.code}), ${this.body?.error?.errorCode}, ${this.body?.error?.message}"
        )
        null
    }
    is NetworkResponse.NetworkError -> {
        Log.e("NetworkResponse", "onErrorReturn: NetworkError", this.error)
        null
    }
    is NetworkResponse.UnknownError -> {
        Log.e("NetworkResponse", "onErrorReturn: UnknownError(${this.code})", this.error)
        null
    }
}
