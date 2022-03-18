package com.example.task_1

import android.app.Application
import com.example.task_1.Repo.Repository
import com.example.task_1.Repo.RepositoryFirebase
import com.example.task_1.Repo.RepositoryRoom
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModdel = module {

            viewModel { MainViewModel() }
            single<Application>{this@MyApplication}
            single<Repository> { RepositoryFirebase(application = get()) }

        }
        startKoin {
           androidContext(applicationContext)
            modules(appModdel)
        }
    }

}