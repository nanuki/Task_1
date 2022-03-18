package com.example.task_1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_1.Repo.Repository
import com.example.task_1.Room.MemberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel(): ViewModel(), KoinComponent {
    val repo :Repository by inject()


    private val _datalist = MutableLiveData<List<MemberEntity>>()
    val datalist : MutableLiveData<List<MemberEntity>> = _datalist


    suspend fun getRecyclerData(){
        viewModelScope.launch(Dispatchers.IO){
       datalist.postValue(repo.getAlldata())
         // Log.d("repoModel", "${repo.getAlldata()}")
      }
    }

   suspend fun  setData(){
        viewModelScope.launch(Dispatchers.IO){
            val memberEntity : MemberEntity = MemberEntity(0, "")
            repo.insertdata(0,memberEntity)
            datalist.postValue(repo.getAlldata())
           //  Log.d("repoid", "$repoid")
        }


    }



}