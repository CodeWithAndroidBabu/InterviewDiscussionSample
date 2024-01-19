package com.neomocktest.deep.ui.auth.repo

import android.net.Uri
import com.neomocktest.deep.data.Users
import com.neomocktest.deep.state.ApiState
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

interface AuthRepo {
    fun loginSignUpUser(users: Users): Flow<ApiState<String>>
    fun uploadUserImg(uuid: Uri?): Flow<ApiState<String>>
}