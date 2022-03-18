package com.example.task_1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var idstring : String = "",
    var name: String = "",
    var surname: String = "")
