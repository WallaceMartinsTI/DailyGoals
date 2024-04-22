package com.wcsm.dailygoals.data.model

import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("motivational_quotes") val motivationalQuotes: List<Quote>
)
