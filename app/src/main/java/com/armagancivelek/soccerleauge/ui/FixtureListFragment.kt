package com.armagancivelek.soccerleauge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.armagancivelek.soccerleauge.R
import com.armagancivelek.soccerleauge.adapter.FixtureListAdapter
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.databinding.FragmentFixtureListBinding
import com.armagancivelek.soccerleauge.viewmodel.SoccerViewModel

class FixtureListFragment(
    private val position: Int,
    private val teamList: Array<Team>
) : Fragment(R.layout.fragment_fixture_list) {

    private lateinit var _binding: FragmentFixtureListBinding
    private lateinit var mViewModel: SoccerViewModel
    private lateinit var adapter1: FixtureListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        init(view)
        observerLiveData()

    }

    fun getMod(position: Int): Int {
        if (teamList.size % 2 == 0) {
            return position % (teamList.size - 1)
        } else
            return position % (teamList.size)
    }

    private fun observerLiveData() {


        mViewModel.getRoundList(getMod(position)).observe(viewLifecycleOwner, {

            if (it.isNotEmpty()) {
                adapter1.run {
                    this.updateList(it as ArrayList<Fixture>, position)
                }
                if (teamList.size % 2 != 0) {
                    _binding.tvPassTeam.run {

                        this.text =
                            "Bay geçen takım :  ${teamList[it[position % it.size].passTeam!!].team_name}"
                        visibility = View.VISIBLE
                    }
                } else
                    _binding.tvPassTeam.visibility = View.GONE

            }


        })

    }

    private fun init(view: View) {
        mViewModel = (requireActivity() as MainActivity).mViewModel
        _binding = FragmentFixtureListBinding.bind(view)
        adapter1 = FixtureListAdapter(arrayListOf(), teamList)
        _binding.fixtureListRecycler.apply {
            adapter = adapter1
            layoutManager = LinearLayoutManager(requireContext())
        }


    }


}