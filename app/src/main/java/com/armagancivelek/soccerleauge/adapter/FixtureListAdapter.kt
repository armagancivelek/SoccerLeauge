package com.armagancivelek.soccerleauge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.databinding.FixtureListItemRowBinding

class FixtureListAdapter(
    private var fixtureList: ArrayList<Fixture>,
    private val teamList: Array<Team>


) : RecyclerView.Adapter<FixtureListAdapter.ViewHolder>() {

    private var halfCount = 1


    inner class ViewHolder(var binding: FixtureListItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FixtureListItemRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    }


    fun updateList(newFixtureList: List<Fixture>, position: Int) {
        halfCount = if (position >= 21) 2 else 1
        fixtureList.clear()
        fixtureList.addAll(newFixtureList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (fixtureList.isNotEmpty()) {
            if (halfCount == 1) {
                holder.binding.tvTeamHome.text =
                    teamList[fixtureList[position].homeTeam!!].team_name
                holder.binding.tvTeamAway.text =
                    teamList[fixtureList[position].awayTeam!!].team_name
            } else {
                holder.binding.tvTeamHome.text =
                    teamList[fixtureList[position].awayTeam!!].team_name
                holder.binding.tvTeamAway.text =
                    teamList[fixtureList[position].homeTeam!!].team_name
            }


        }


    }

    override fun getItemCount(): Int {
        return fixtureList.size

    }

}