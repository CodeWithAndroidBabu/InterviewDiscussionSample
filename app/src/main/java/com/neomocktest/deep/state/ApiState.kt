package com.neomocktest.deep.state

/**
 * @Author: Deep raj
 * @Date: 19/12/23
 */

sealed class ApiState<out T> {
  object Loading:ApiState<Nothing>()
  data class Error(val msg: String):ApiState<Nothing>()
  data class Success<T>(val data: T?):ApiState<T>()
  data class Info(val msg: String):ApiState<Nothing>()
}