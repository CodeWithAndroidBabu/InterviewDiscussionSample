package com.neomocktest.deep.ui.home.repo

import com.neomocktest.deep.state.ApiState
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Deep raj
 * @Date: 23/01/24
 */

interface HomeRepo {
    fun getQuestions(): Flow<ApiState<Any>>
    fun getDiscussions(): Flow<ApiState<Any>>
    fun replyAnswer(): Flow<ApiState<Any>>
}