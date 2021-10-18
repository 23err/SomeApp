package com.example.someapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.someapp.App
import com.example.someapp.R
import com.example.someapp.databinding.FragmentUserBinding
import com.example.someapp.presenter.GithubUser
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView {

    private lateinit var vb: FragmentUserBinding
    private val presenter by moxyPresenter { UserPresenter(App.INSTANCE.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            val githubUser = it.getParcelable<GithubUser>(GITHUB_USER_EXTRA)
            githubUser?.let {
                presenter.showLogin(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentUserBinding.inflate(inflater, container, false)
        return vb.root
    }

    companion object{
        private val GITHUB_USER_EXTRA = "github user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(GITHUB_USER_EXTRA, user)
            this.arguments = bundle
        }
    }

    override fun showLogin(text: String) {
        vb.tvLogin.text = text
    }
}