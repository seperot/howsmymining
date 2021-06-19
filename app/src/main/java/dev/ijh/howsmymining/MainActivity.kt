package dev.ijh.howsmymining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.ijh.howsmymining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onResume() {
    super.onResume()
    binding.btnFind.setOnClickListener(View.OnClickListener {
      binding.etWalletId.error = null
      if(binding.etWalletId.text.toString().length <= 10) {
        binding.etWalletId.error = "Not a TRTL wallet address"
        binding.tvWorkers.text = ""
        binding.tvHashrate.text = ""
        binding.tvLastBlock.text = ""
          return@OnClickListener
      }
        binding.tvWorkers.text = getString(R.string.workers, null ?: getString(R.string.no_workers))
        binding.tvHashrate.text = getString(R.string.hashreate, null ?: getString(R.string.no_hashrate))
        binding.tvLastBlock.text = getString(R.string.last_block, null ?: getString(R.string.no_blocks))
    })
  }
}