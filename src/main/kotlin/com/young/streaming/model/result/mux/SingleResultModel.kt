package com.young.streaming.model.result.mux

import com.google.gson.annotations.SerializedName
import com.young.streaming.model.result.mux.ResultData

data class SingleResultModel(
    @SerializedName("data")
    val resultData: ResultData
)
