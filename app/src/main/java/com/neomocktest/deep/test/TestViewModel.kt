package com.neomocktest.deep.test

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * @Author: Deep raj
 * @Date: 08/01/24
 */


class TestViewModel: ViewModel() {

    private var repo: Repo? = null

    fun setRepo(repo: Repo?) {
        this@TestViewModel.repo = repo
    }

    fun executeOne() = repo?.getDataThree()

    fun executeFour() = repo?.getDataFour()






}