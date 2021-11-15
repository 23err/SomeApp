package com.example.someapp.di

import com.example.someapp.presenter.MainPresenter
import com.example.someapp.presenter.RepoPresenter
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UsersPresenter
import com.example.someapp.view.MainActivity
import com.example.someapp.view.UserFragment
import com.example.someapp.view.UsersFragment
import com.example.someapp.view.UsersRVAdapter
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        RepoModule::class,
        ApiModule::class,
        CacheModule::class,
        AppModule::class,
        SchedulerModule::class,
        LoaderModule::class,
    ]
)
interface AppComponent {
    fun inject(usersPresenter: UsersPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(userFragment: UserFragment)
    fun inject(usersRVAdapter: UsersRVAdapter)
    fun reposSubcomponent(): ReposSubcomponent
}

@UserScope
@Subcomponent(modules = [UserModule::class])
interface ReposSubcomponent {
    fun inject(userPresenter: UserPresenter)
}