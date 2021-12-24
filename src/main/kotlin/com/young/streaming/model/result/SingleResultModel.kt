package com.young.streaming.model.result

import com.google.gson.annotations.SerializedName

data class SingleResultModel(
    @SerializedName("data")
    val resultData: ResultData
)
