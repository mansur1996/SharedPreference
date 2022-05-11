package com.example.localization.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.localization.databinding.ActivitySharedPreferenceBinding
import com.example.localization.manager.PrefsManager

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferenceBinding
    private lateinit var context: Context
    private lateinit var keys : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        keys = ArrayList()
        initViews()
    }

    private fun initViews() {
        binding.btnSave.setOnClickListener {
            val email = binding.etText.text.toString().trim()
            val key = binding.etKey.text.toString().trim()
            if(key != ""){
                PrefsManager.getInstance(context)!!.saveData(key, email)
                keys.add(key)
                Toast.makeText(context, "Saved Email", Toast.LENGTH_SHORT).show()
                binding.etText.setText("")
                binding.etKey.setText("")
            }else{
                Toast.makeText(context, "Enter key", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnRemoveData.setOnClickListener {
            val key = binding.etKey.text.toString().trim()
            if(key != ""){
                PrefsManager.getInstance(context)!!.removeData(key)
                Toast.makeText(context, "Email removed Successfully", Toast.LENGTH_SHORT).show()
                keys.remove(key)
            }else{
                Toast.makeText(context, "Enter key", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRemoveAllData.setOnClickListener {
            PrefsManager.getInstance(context)!!.removeAllData()
            keys.clear()
            Toast.makeText(context, "All data removed Successfully", Toast.LENGTH_SHORT).show()
        }

        binding.btnLoad.setOnClickListener {
            var data : String = ""
            for(key in keys){
                data += PrefsManager.getInstance(context)!!.loadData(key) + "\n"
            }

            Toast.makeText(context, "Email loaded Successfully", Toast.LENGTH_SHORT).show()
            binding.tvData.text = data
        }

    }
}