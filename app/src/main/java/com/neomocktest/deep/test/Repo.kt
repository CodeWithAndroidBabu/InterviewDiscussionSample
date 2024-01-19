package com.neomocktest.deep.test

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Deep raj
 * @Date: 08/01/24
 */

class Repo {




    fun getDataOne(): kotlinx.coroutines.flow.Flow<Int> = callbackFlow<Int> {
        for (i in 1..11){
            trySend(i)
        }
        awaitClose {
            close()
        }
    }

    fun getDataThree(): kotlinx.coroutines.flow.Flow<Int> =  flow {
        val list = listOf(1,2,3,4,5,6)
        list.forEach {
            emit(it)
        }
    }

    fun getDataFour(): kotlinx.coroutines.flow.Flow<Int> = callbackFlow<Int> {
        for (i in 1..11){
            trySend(i)
        }
        awaitClose {
            close()
        }
    }
}