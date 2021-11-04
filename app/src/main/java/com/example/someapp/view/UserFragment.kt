package com.example.someapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.someapp.AndroidNetworkStatus
import com.example.someapp.App
import com.example.someapp.R
import com.example.someapp.databinding.FragmentUserBinding
import com.example.someapp.model.GithubUsersRepo
import com.example.someapp.model.RetrofitGithub
import com.example.someapp.model.RetrofitGithubRepositoriesRepo
import com.example.someapp.model.database.RoomGithubRepositoryCache
import com.example.someapp.presenter.GithubUser
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView {

    private lateinit var binding: FragmentUserBinding
    private val presenter by moxyPresenter {
        UserPresenter(
            App.INSTANCE.router,
            RetrofitGithubRepositoriesRepo(
                RetrofitGithub.api,
                AndroidNetworkStatus(requireContext()),
                RoomGithubRepositoryCache(App.INSTANCE.db)
            ),
            AndroidSchedulers.mainThread(),
            UsersScreen()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val githubUser = it.getParcelable<GithubUser>(GITHUB_USER_EXTRA)
            githubUser?.let { githubUser ->
                presenter.init(githubUser)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showLogin(text: String) {
        binding.tvLogin.text = text
    }

    override fun loadAvatar(url: String) {
        GlideImageLoader().loadInto(url, binding.imageView)
    }

    override fun initRepos() {
        binding.rvRepos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ReposRVAdapter(presenter.reposListPresenter)
        }
    }

    override fun updateReposList() {
        binding.rvRepos.adapter?.notifyDataSetChanged()
    }

    companion object {
        private val GITHUB_USER_EXTRA = "github user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(GITHUB_USER_EXTRA, user)
            this.arguments = bundle
        }
    }
}