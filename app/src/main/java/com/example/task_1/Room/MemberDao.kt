package com.example.task_1.Room

import androidx.room.*
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(member:MemberEntity)

    @Query("SELECT * FROM MemberEntity")
    fun getAllData():MutableList<MemberEntity>

    @Query("SELECT * FROM MemberEntity WHERE id LIKE :id")
    fun getDatabyId(id :Int):MutableList<MemberEntity>

    @Query("DElETE FROM MemberEntity WHERE id LIKE :id")
    fun deleteDatabyId(id: Int)
}