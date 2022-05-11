package com.example.localization.activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.localization.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        binding.btnOpenActivity.setOnClickListener {
            SharedPreferenceActivity()
        }

    }

    private fun SharedPreferenceActivity() {
        val intent = Intent(this, SharedPreferenceActivity::class.java)
        startActivity(intent)
    }


    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Really Exit?")
            .setMessage("Are you sure you want to exit?")
            .setNegativeButton("No", null)
            .setPositiveButton("Yes"
            ) { _, _ ->
                super@MainActivity.onBackPressed()
            }
            .create()
            .show()
    }

}