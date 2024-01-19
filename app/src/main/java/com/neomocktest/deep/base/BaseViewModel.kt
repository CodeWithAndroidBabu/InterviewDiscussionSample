package com.neomocktest.deep.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {
}