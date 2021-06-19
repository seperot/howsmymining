package dev.ijh.howsmymining.data

import com.google.gson.annotations.SerializedName

data class TRTLSoloPool(
  @SerializedName("address") var address: String? = null,
  @SerializedName("workers") var workers: Int? = 0,
  @SerializedName("hashrate") var hashrate: Int? = 0,
  @SerializedName("last_block") var last_block: Int? = 0,
  @SerializedName("last_block_hash") var last_block_hash: String? = null,
  )
