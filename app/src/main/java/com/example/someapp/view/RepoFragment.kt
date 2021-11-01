package com.example.someapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.someapp.databinding.FragmentRepoBinding
import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.RepoPresenter
import com.example.someapp.presenter.RepoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView {

    private lateinit var binding: FragmentRepoBinding
    private val presenter by moxyPresenter { RepoPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<GithubRepo>(GITHUB_REPO)?.let{
            presenter.init(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private val GITHUB_REPO = "github repo"
        fun newInstance(githubRepo: GithubRepo): RepoFragment {
            return RepoFragment().apply {
                val bundle = Bundle().apply {
                    putParcelable(GITHUB_REPO, githubRepo)
                }
                arguments = bundle
            }
        }
    }

    override fun init(githubRepo: GithubRepo) {
        binding.apply {
            tvRepoName.text = githubRepo.name
            tvFork.text = githubRepo.forks.toString()
        }
    }
}