package com.rebus.settingstelegrambot.data.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class StatusWebHook {
    @SerializedName("ok")
    @Expose
    var ok: Boolean? = null

    @SerializedName("result")
    @Expose
    var result: Result? = null
}

class Result {
    @SerializedName("last_error_message")
    @Expose
    var lastErrorMessage: String? = null

    @SerializedName("ip_address")
    @Expose
    var ipAddress: String? = null
}

class StateSendMessage {
    @SerializedName("ok")
    @Expose
    var ok: Boolean? = null

    @SerializedName("result")
    @Expose
    var result: ResultSendMessage? = null
}

class ResultSendMessage {
    @SerializedName("message_id")
    @Expose
    var messageId: Int? = null

    @SerializedName("date")
    @Expose
    var date: Int? = null

    @SerializedName("text")
    @Expose
    var text: String? = null
}