package com.example.task_1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemberEntity::class], version = 1)
 abstract class DBRoom: RoomDatabase() {

  abstract fun getMemberDao(): MemberDao

    companion object{
     private var INSTANCE : DBRoom? = null
     fun getInstence(context: Context): DBRoom{
         return INSTANCE?:Room.databaseBuilder(context.applicationContext,DBRoom::class.java,"member_data").build()



     }
    }
}