package com.example.someapp.di

import com.example.someapp.presenter.MainPresenter
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UsersPresenter
import com.example.someapp.view.MainActivity
import com.example.someapp.view.UserFragment
import com.example.someapp.view.UsersFragment
import com.example.someapp.view.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CiceroneModule::class,
    RepoModule::class,
    ApiModule::class,
    CacheModule::class,
    AppModule::class,
    SchedulerModule::class,
    LoaderModule::class,
])
interface AppComponent {
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(userFragment: UserFragment)
    fun inject(usersRVAdapter: UsersRVAdapter)
}