package com.neomocktest.deep.ui.auth

import android.net.Uri
import com.neomocktest.deep.base.BaseViewModel
import com.neomocktest.deep.data.Users
import com.neomocktest.deep.ui.auth.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

@HiltViewModel
class AuthViewModel @Inject constructor(val authRepo: AuthRepo): BaseViewModel() {

    fun loginSignUpUser(users: Users) = authRepo.loginSignUpUser(users)
    fun uploadUserImg(uuid: Uri?) = authRepo.uploadUserImg(uuid)
}