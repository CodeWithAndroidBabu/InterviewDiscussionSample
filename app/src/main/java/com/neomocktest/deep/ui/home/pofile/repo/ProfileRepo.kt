package com.neomocktest.deep.ui.home.pofile.repo

import com.neomocktest.deep.data.Users
import com.neomocktest.deep.state.ApiState
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Deep raj
 * @Date: 25/01/24
 */

interface ProfileRepo {
    fun getUserDetails(): Flow<ApiState<Users>>
}