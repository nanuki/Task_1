package com.example.task_1.Repo

import com.example.task_1.Room.MemberEntity

interface Repository {

    var recyclerdatalist : MutableList<MemberEntity>

    suspend fun insertdata(id : Int, memberEntity: MemberEntity){

    }

    suspend fun getAlldata(): MutableList<MemberEntity>{
        return recyclerdatalist
    }

    suspend fun getDatabyId(id:Int): MutableList<MemberEntity>{
       return recyclerdatalist
    }

    suspend fun deletDatabyId(id: Int, idstring : String){

    }


}