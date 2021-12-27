package com.young.interaction.model.result.mux

import com.google.gson.annotations.SerializedName

data class NewAssetSettings(
    @SerializedName("playback_policies")
    val playbackPolicies: Array<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewAssetSettings

        if (!playbackPolicies.contentEquals(other.playbackPolicies)) return false

        return true
    }

    override fun hashCode(): Int {
        return playbackPolicies.contentHashCode()
    }
}
