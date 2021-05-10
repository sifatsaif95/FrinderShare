package com.britishbroadcast.frindershare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.britishbroadcast.frindershare.model.FrinderRepository
import com.britishbroadcast.frindershare.model.data.FrinderPost

class FrinderViewModel: ViewModel(){

    private val frinderRepository = FrinderRepository()

    fun getPostData(): LiveData<List<FrinderPost>> = frinderRepository.postLiveData

    fun postThePost(post: FrinderPost) = frinderRepository.addFrinderPost(post)

}