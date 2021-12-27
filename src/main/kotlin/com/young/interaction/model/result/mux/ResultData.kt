package com.young.interaction.model.result.mux

import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("stream_key")
    val streamKey: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("reconnect_window")
    val reconnectWindow: Int,
    @SerializedName("playback_ids")
    val playbackIds: Array<PlaybackIds>,
    @SerializedName("new_asset_settings")
    val newAssetSettings: NewAssetSettings,
    @SerializedName("id")
    val streamId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("latency_mode")
    val latencyMode: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResultData

        if (streamKey != other.streamKey) return false
        if (status != other.status) return false
        if (reconnectWindow != other.reconnectWindow) return false
        if (!playbackIds.contentEquals(other.playbackIds)) return false
        if (newAssetSettings != other.newAssetSettings) return false
        if (streamId != other.streamId) return false
        if (createdAt != other.createdAt) return false
        if (latencyMode != other.latencyMode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = streamKey.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + reconnectWindow
        result = 31 * result + playbackIds.contentHashCode()
        result = 31 * result + newAssetSettings.hashCode()
        result = 31 * result + streamId.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + latencyMode.hashCode()
        return result
    }
}
