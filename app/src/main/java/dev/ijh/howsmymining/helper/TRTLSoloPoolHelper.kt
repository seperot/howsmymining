package dev.ijh.howsmymining.helper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dev.ijh.howsmymining.networking.TRTLSoloPool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TRTLSoloPoolHelper()  {

  companion object {
    private fun getLatestValues(): Call<TRTLSoloPool.SoloPool> {
      return TRTLSoloPool.GetPoolInfo.create().getPoolInfo("stats/${address.toString()}")
    }

    var address : String = ""

    val workers: MutableLiveData<String> by lazy {
      MutableLiveData<String>()
    }
    val hashrate: MutableLiveData<String> by lazy {
      MutableLiveData<String>()
    }
    val lastBlock: MutableLiveData<String> by lazy {
      MutableLiveData<String>()
    }

    fun getValue() {
      try {
        getLatestValues().enqueue(object : Callback<TRTLSoloPool.SoloPool> {
          override fun onFailure(call: Call<TRTLSoloPool.SoloPool>?, t: Throwable?) {
            Log.v("retrofit", "Pool not responding")
          }

          override fun onResponse(
            call: Call<TRTLSoloPool.SoloPool>?,
            response: Response<TRTLSoloPool.SoloPool>?
          ) {
            workers.value = ""
            hashrate.value = ""
            lastBlock.value = ""
            response?.body()?.let {
              workers.value = it.workers.toString()
              hashrate.value = it.hashrate.toString()
              lastBlock.value = it.last_block_hash ?: ""
            }
          }
        })
      } catch (exception: Exception) {
        Log.d(exception.toString(), exception.toString())
      }
    }
  }
}