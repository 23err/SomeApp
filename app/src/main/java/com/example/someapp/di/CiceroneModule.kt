package com.example.someapp.di

import android.widget.ImageView
import androidx.room.Room
import com.example.someapp.AndroidNetworkStatus
import com.example.someapp.App
import com.example.someapp.INetworkStatus
import com.example.someapp.model.*
import com.example.someapp.model.database.*
import com.example.someapp.view.AndroidScreens
import com.example.someapp.view.GlideImageLoader
import com.example.someapp.view.IImageLoader
import com.example.someapp.view.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Scope
import javax.inject.Singleton

@Module
class CiceroneModule {
    private var cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()

}

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoomGithubUserCache
    ): IGithubUserRepo = GithubUsersRepo(api, networkStatus, cache)


}

@Module
class UserModule{
    @UserScope
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoomGithubRepositoryCache
    ): IRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope

@Module
class ApiModule {
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)

    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: AppDatabase): IRoomGithubUserCache = RoomGithubUserCache(database)

    @Singleton
    @Provides
    fun reposCache(database: AppDatabase): IRoomGithubRepositoryCache =
        RoomGithubRepositoryCache(database)
}

@Module
class LoaderModule {
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App = app
}

@Module
class SchedulerModule {
    @Provides
    fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}