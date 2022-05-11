package com.example.localization.manager

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {
    private val sharedPreferences : SharedPreferences?

    init {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    companion object{
        private var prefsManager : PrefsManager? = null

        fun getInstance(context: Context) : PrefsManager?{
            if(prefsManager == null){
                prefsManager = PrefsManager(context)
            }
            return prefsManager
        }
    }

    fun saveData(key: String?, value : String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun loadData(key : String?) : String? {
        return if(sharedPreferences != null) sharedPreferences.getString(key, "defaultEmail@gmail.com") else ""
    }

    fun removeData(key : String?){
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        editor.apply()
    }
    fun removeAllData(){
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }
}