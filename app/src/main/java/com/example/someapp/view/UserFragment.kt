package com.example.someapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.someapp.App
import com.example.someapp.R
import com.example.someapp.databinding.FragmentUserBinding
import com.example.someapp.presenter.GithubUser
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView {

    private lateinit var binding: FragmentUserBinding
    private val presenter by moxyPresenter {
        UserPresenter().apply {App.instance.initReposSubcomponent().inject(this)}
    }
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseReposSubcomponent()
    }

    override fun showLogin(text: String) {
        binding.tvLogin.text = text
    }

    override fun loadAvatar(url: String) {
        imageLoader.loadInto(url, binding.imageView)
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