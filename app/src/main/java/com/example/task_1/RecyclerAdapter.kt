package com.example.task_1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.task_1.Repo.Repository
import com.example.task_1.Room.MemberEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecyclerAdapter(val context: Context, val data: List<MemberEntity>) :
    RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>(), KoinComponent{
    val repo:Repository by inject()
    val viewModel: MainViewModel by inject()

    private var listData :MutableList<MemberEntity> = data as MutableList<MemberEntity>


    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val delete : ImageView

        init {
            name = itemView.findViewById(R.id.name)
            delete = itemView.findViewById(R.id.imageView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.row,parent,false)
        return CustomViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentData = data[getIndexbyPostition(position)]
        holder.name.setText(currentData.name)
        holder.delete.setOnClickListener(View.OnClickListener {
            val id = currentData.id
            val idstring = currentData.idstring
//            val name = currentData.name
//            val surname = currentData.surname
//            val entity = currentData.copy(id,name,surname)
            Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
            GlobalScope.launch(Dispatchers.IO){
                repo.deletDatabyId(id, idstring)
                viewModel.getRecyclerData()
                
            }

            deleteRowItem(getIndexbyPostition(position))
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getIndexbyPostition(position: Int):Int{
        return position % data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteRowItem(position: Int){
        listData.removeAt(position)
        notifyDataSetChanged()
    }
}