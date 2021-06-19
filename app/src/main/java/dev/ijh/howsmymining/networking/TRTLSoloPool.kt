package dev.ijh.howsmymining.networking

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


class TRTLSoloPool(val walletId: String) {

  data class SoloPool(
    @SerializedName("address") var address: String? = null,
    @SerializedName("workers") var workers: Int? = 0,
    @SerializedName("hashrate") var hashrate: Int? = 0,
    @SerializedName("last_block") var last_block: Int? = 0,
    @SerializedName("last_block_hash") var last_block_hash: String? = null,
  )

  interface GetPoolInfo {
    @GET
    fun getPoolInfo(@Url url: String?): Call<SoloPool>

    companion object Factory {
      private const val BASE_URL = "https://solo.turtlecoin.dev/"
      fun create(): GetPoolInfo {
        val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
        return retrofit.create(GetPoolInfo::class.java)
      }
    }
  }

}
