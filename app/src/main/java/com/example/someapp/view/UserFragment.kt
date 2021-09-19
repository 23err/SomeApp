package com.example.someapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.someapp.App
import com.example.someapp.databinding.FragmentUserBinding
import com.example.someapp.presenter.UserPresenter
import com.example.someapp.presenter.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), BackButtonListener, UserView {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.INSTANCE.router)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val login = args?.getString(LOGIN)
        presenter.showLogin(login)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun showLogin(text: String) {
        binding.tvLogin.text = text
    }

    companion object {
        private const val LOGIN = "LOGIN"
        fun newInstance(text: String): Fragment = UserFragment().apply {
            arguments = Bundle().apply { putString(LOGIN, text) }
        }
    }
}