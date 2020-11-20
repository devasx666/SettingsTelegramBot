package com.rebus.settingstelegrambot.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseTelegram(
    @SerializedName("ok")
    @Expose
    val ok: Boolean,

    @SerializedName("result")
    @Expose
    val result: Boolean,

    @SerializedName("description")
    @Expose
    val description: String
)
