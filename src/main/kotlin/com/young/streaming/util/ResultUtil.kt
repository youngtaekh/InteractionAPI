package com.young.streaming.util

import com.google.gson.Gson
import com.young.streaming.model.StreamModel
import com.young.streaming.model.result.SingleResultModel

class ResultUtil {
    fun setPostStream(streamModel: StreamModel, result: String?): Int {
        if (result.isNullOrEmpty()) {
            val resultModel = Gson().fromJson(result, SingleResultModel::class.java)
            println("setPostStream $resultModel")
            streamModel.streamId = resultModel.resultData.streamId
            streamModel.streamKey = resultModel.resultData.streamKey
            if (resultModel.resultData.playbackIds.isNotEmpty()) {
                streamModel.playbackId = resultModel.resultData.playbackIds[0].id
                streamModel.policy = resultModel.resultData.playbackIds[0].policy
            }
            streamModel.status = resultModel.resultData.status
            return 1
        }
        return 0
    }

    fun getStreamId(result: String?): String {
        if (result != null) {
            val resultModel = Gson().fromJson(result, SingleResultModel::class.java)
            println("getStreamId $resultModel")
            return resultModel.resultData.streamId
        }
        return ""
    }

    fun getStreamKey(result: String?): String {
        if (result != null) {
            val resultModel = Gson().fromJson(result, SingleResultModel::class.java)
            println(resultModel)
            return resultModel.resultData.streamKey
        }
        return ""
    }
}
