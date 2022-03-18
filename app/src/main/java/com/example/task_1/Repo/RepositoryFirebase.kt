package com.example.task_1.Repo

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.task_1.Room.DBRoom
import com.example.task_1.Room.MemberEntity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder

class RepositoryFirebase(application: Application):Repository {
    val context = application


    override  var recyclerdatalist : MutableList<MemberEntity> = mutableListOf()

    override suspend  fun insertdata(id : Int,memberEntity: MemberEntity){
        
        val firestore = FirebaseFirestore.getInstance()
        val userCollRef = firestore.collection("memberEntity").document()
        memberEntity.surname =userCollRef.id
        memberEntity.idstring = userCollRef.id
        memberEntity.name = userCollRef.id
        userCollRef.get().await()
        userCollRef.set(memberEntity)

    }

    override suspend fun getAlldata(): MutableList<MemberEntity>{

            val list : MutableList<MemberEntity> = mutableListOf()
            val firestore = FirebaseFirestore.getInstance()
            val userCollRef = firestore.collection("memberEntity")
            val snapshot = userCollRef.get().await()
            for (document in snapshot){
                val datatoObjects = document.toObject<MemberEntity>()
                list .add(datatoObjects)

            }
            recyclerdatalist = list

        return recyclerdatalist
    }





    override suspend fun deletDatabyId(id: Int, idstring : String){
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("memberEntity").document(idstring).delete()
    }



}