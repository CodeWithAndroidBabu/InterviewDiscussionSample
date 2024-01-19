package com.neomocktest.deep.shared

import android.content.SharedPreferences
import com.google.gson.Gson
import com.neomocktest.deep.data.Users
import com.neomocktest.utility.AppConstant
import javax.inject.Inject

/**
 * @Author: Deep raj
 * @Date: 26/12/23
 */

@Suppress("UNCHECKED_CAST")
class SharedRepoImp @Inject constructor(val sharedPref: SharedPreferences, val gson: Gson) :
    SharedRepo {

    override fun saveValue(key: String, value: Any?) {
        when (value) {
            is String -> {
                sharedPref.edit()?.putString(key, value)?.apply()
            }

            is Float -> {
                sharedPref.edit()?.putFloat(key, value)?.apply()
            }

            is Boolean -> {
                sharedPref.edit()?.putBoolean(key, value)?.apply()
            }

            is Int -> {
                sharedPref.edit()?.putInt(key, value)?.apply()
            }

            else -> throw IllegalArgumentException("Value can be store only String, Float, Boolean and Int only")
        }

    }

    override fun saveUser(key: String, value: Users) {
        saveValue(key, gson.toJson(value))
    }

    override fun getUser(key: String): Users {
        val json = sharedPref.getString(AppConstant.FBRoomName.USERS, "") ?: ""
        return if (json.isEmpty()) Users() else gson.fromJson<Users>(
            json,
            Users::class.java
        ) as Users
    }

    override fun <T> getValue(key: String): T? {
        sharedPref.all?.forEach {
            if (it.key == key) {
                return it.value as T?
            }
        }
        return null
    }


    override fun deleteValue(key: String) {
        sharedPref.edit()?.remove(key)?.apply()
    }

    override fun clearShared() {
        sharedPref.edit()?.clear()?.apply()
    }

}