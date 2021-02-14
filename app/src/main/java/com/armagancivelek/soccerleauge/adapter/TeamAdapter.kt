package com.armagancivelek.soccerleauge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.databinding.TeamItemRowBinding

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: TeamItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.team_id == newItem.team_id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TeamItemRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTeamName.text = differ.currentList[position].team_name


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}