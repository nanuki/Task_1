package com.example.task_1.Repo

import android.app.Application
import com.example.task_1.Room.DBRoom
import com.example.task_1.Room.MemberEntity

class RepositoryRoom(application: Application):Repository {
    val context = application

    override  var recyclerdatalist : MutableList<MemberEntity> = mutableListOf()

    override suspend fun insertdata(id : Int, memberEntity: MemberEntity){
     DBRoom.getInstence(context).getMemberDao().insertData(memberEntity)
    }

    override suspend fun getAlldata(): MutableList<MemberEntity>{
        return DBRoom.getInstence(context).getMemberDao().getAllData()
    }

    override suspend fun getDatabyId(id:Int): MutableList<MemberEntity>{
        return DBRoom.getInstence(context).getMemberDao().getDatabyId(id)
    }

    override suspend fun deletDatabyId(id: Int, idstring : String){
        DBRoom.getInstence(context).getMemberDao().deleteDatabyId(id)

    }

}