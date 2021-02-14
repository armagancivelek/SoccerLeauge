package com.armagancivelek.soccerleauge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.armagancivelek.soccerleauge.R
import com.armagancivelek.soccerleauge.adapter.TeamAdapter
import com.armagancivelek.soccerleauge.databinding.FragmentHomeBinding
import com.armagancivelek.soccerleauge.utils.NetworkResult
import com.armagancivelek.soccerleauge.viewmodel.SoccerViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var mViewModel: SoccerViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var teamsAdapter: TeamAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        observeLiveData()


    }

    private fun observeLiveData() {
        mViewModel.teams.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    showButton()
                    hideProgressBar()
                    response.let {
                        teamsAdapter.differ.submitList(response.data)

                    }

                }

                is NetworkResult.Error -> {
                    hideProgressBar()


                }
                is NetworkResult.Loading -> {

                    showProgressBar()
                    hideButton()

                }
            }

        })
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun hideButton() {
        binding.btnFixture.visibility = View.INVISIBLE
    }

    private fun showButton() {
        binding.btnFixture.visibility = View.VISIBLE
    }

    private fun init(view: View) {
        teamsAdapter = TeamAdapter()
        mViewModel = (activity as MainActivity).mViewModel
        binding = FragmentHomeBinding.bind(view)
        binding.teamsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = teamsAdapter

        }


    }
}
