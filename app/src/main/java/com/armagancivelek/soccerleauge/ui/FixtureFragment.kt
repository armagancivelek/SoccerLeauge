package com.armagancivelek.soccerleauge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.armagancivelek.soccerleauge.R
import com.armagancivelek.soccerleauge.adapter.FixtureAdapter
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.databinding.FragmentFixtureBinding
import com.armagancivelek.soccerleauge.viewmodel.SoccerViewModel


class FixtureFragment : Fragment(R.layout.fragment_fixture) {
    private lateinit var mViewModel: SoccerViewModel
    private lateinit var _binding: FragmentFixtureBinding
    private lateinit var adapter: FixtureAdapter
    private val args: FixtureFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observerLiveData()

    }


    private fun observerLiveData() {


        mViewModel.getSavedFixture().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it as ArrayList<Fixture>)
        })


    }


    private fun init(view: View) {

        _binding = FragmentFixtureBinding.bind(view)
        mViewModel = (requireActivity() as MainActivity).mViewModel

        adapter = FixtureAdapter(activity?.supportFragmentManager!!, arrayListOf(), args.teamList)
        _binding.fixtureViewPager.adapter = adapter
        _binding.fixtureTabLayout.setupWithViewPager(_binding.fixtureViewPager, true)


    }


    override fun onDestroy() {
        super.onDestroy()
        mViewModel.deleteAllFixture()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.deleteAllFixture()
    }

}