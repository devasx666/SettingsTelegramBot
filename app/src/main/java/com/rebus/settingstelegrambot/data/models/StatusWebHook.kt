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
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("has_custom_certificate")
    @Expose
    var hasCustomCertificate: Boolean? = null

    @SerializedName("pending_update_count")
    @Expose
    var pendingUpdateCount: Int? = null

    @SerializedName("last_error_date")
    @Expose
    var lastErrorDate: Int? = null

    @SerializedName("last_error_message")
    @Expose
    var lastErrorMessage: String? = null

    @SerializedName("max_connections")
    @Expose
    var maxConnections: Int? = null

    @SerializedName("ip_address")
    @Expose
    var ipAddress: String? = null
}