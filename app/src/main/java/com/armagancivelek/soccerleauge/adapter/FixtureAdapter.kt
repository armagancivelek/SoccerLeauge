package com.armagancivelek.soccerleauge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.ui.FixtureListFragment

class FixtureAdapter(
    fragmentManager: FragmentManager,
    private var fixtureList: ArrayList<Fixture>,
    private var teamList: Array<Team>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getCount(): Int =
        if (fixtureList.isNotEmpty()) (fixtureList[fixtureList.size - 1].roundCount!!) * 2 + 2 else fixtureList.size

    override fun getItem(position: Int): Fragment {
        return FixtureListFragment(position, teamList)
    }

    fun updateList(newFixtureList: ArrayList<Fixture>) {
        fixtureList.clear()
        fixtureList = newFixtureList
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? = "${position + 1}. Hafta"


}