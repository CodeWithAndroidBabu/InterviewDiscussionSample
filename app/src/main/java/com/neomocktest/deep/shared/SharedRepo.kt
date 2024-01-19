package com.neomocktest.deep.shared

import com.neomocktest.deep.data.Users

/**
 * @Author: Deep raj
 * @Date: 26/12/23
 */

interface SharedRepo {
    fun saveValue(key: String, value: Any?)
    fun saveUser(key: String,value: Users)
    fun getUser(key: String): Users
    fun <T> getValue(key: String):T?
    fun deleteValue(key: String)
    fun clearShared()
}