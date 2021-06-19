package dev.ijh.howsmymining

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.ijh.howsmymining.databinding.ActivityMainBinding
import dev.ijh.howsmymining.helper.TRTLSoloPoolHelper


class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onResume() {
    super.onResume()
      val worker = Observer<String> {
        if (it.isEmpty()) {
          binding.tvWorkers.text = getString(R.string.no_workers)
        } else {
          binding.tvWorkers.text = getString(R.string.workers, it)
        }
      }

      val hashrate = Observer<String> {
        if (it.isEmpty()) {
          binding.tvHashrate.text = getString(R.string.no_hashrate)
        } else {
          binding.tvHashrate.text = getString(R.string.hashreate, it)
        }
      }

      val block = Observer<String> { block ->
        if (block.isEmpty()) {
          binding.tvLastBlock.text = getString(R.string.no_blocks)
        } else {
          binding.tvLastBlock.text =
            getString(R.string.last_block, block)
          binding.tvLastBlock.setOnClickListener(View.OnClickListener {
          val uri: Uri = Uri.parse("https://explorer.turtlecoin.lol/block.html?hash=$block")
          val intent = Intent(Intent.ACTION_VIEW, uri)
          startActivity(intent)
          })
        }
      }

    TRTLSoloPoolHelper.workers.observe(this, worker)
    TRTLSoloPoolHelper.hashrate.observe(this, hashrate)
    TRTLSoloPoolHelper.lastBlock.observe(this, block)

    binding.btnFind.setOnClickListener(
        View.OnClickListener {
          binding.etWalletId.error = null
          binding.tvWorkers.text = ""
          binding.tvHashrate.text = ""
          binding.tvLastBlock.text = ""
          if (binding.etWalletId.text.toString().length <= 10) {
            binding.etWalletId.error = "Not a TRTL wallet address"
            return@OnClickListener
          }
          TRTLSoloPoolHelper.address = binding.etWalletId.text.toString()
          TRTLSoloPoolHelper.getValue()
        })
    }
}
