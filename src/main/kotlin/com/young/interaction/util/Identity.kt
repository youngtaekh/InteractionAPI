package com.young.interaction.util

class Identity {
    companion object {
        private const val TAG = "Identity"

        fun get(): String {
            return System.currentTimeMillis().toString(16)
        }

        fun get(key: String): String {
            return System.currentTimeMillis().toString(16)
        }
    }
}
