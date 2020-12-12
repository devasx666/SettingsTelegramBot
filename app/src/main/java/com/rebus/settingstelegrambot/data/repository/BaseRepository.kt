package com.rebus.settingstelegrambot.data.repository

import android.util.Log
import retrofit2.Response
import com.rebus.settingstelegrambot.data.objects.Result
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCell(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success -> {
                data = result.data
            }
            is Result.Error -> {
                Log.d("TSET", result.exception.toString())
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()

        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("$errorMessage ->> ${response.message()}"))
    }
}
