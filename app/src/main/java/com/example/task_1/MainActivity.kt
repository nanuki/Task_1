package com.example.task_1

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_1.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), KoinComponent{
    var customAdapter:RecyclerAdapter? = null
    val viewModel :MainViewModel by viewModel()
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingEvent = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingEvent.root)



        viewModel.datalist.observe(this, Observer {
            customAdapter = RecyclerAdapter(this,it)
            bindingEvent.recycler.adapter = customAdapter
            bindingEvent.recycler.layoutManager =LinearLayoutManager(this)
            customAdapter!!.notifyDataSetChanged()
        })

        GlobalScope.launch(Dispatchers.IO){
            viewModel.getRecyclerData()

        }

        bindingEvent.imageView.setOnClickListener(View.OnClickListener {
           GlobalScope.launch(Dispatchers.IO){
               viewModel.setData()


           }



        })

    }



}