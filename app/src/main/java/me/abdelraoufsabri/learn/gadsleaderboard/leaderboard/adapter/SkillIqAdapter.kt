package me.abdelraoufsabri.learn.gadsleaderboard.leaderboard.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_top_learning_hours_leader.view.*
import me.abdelraoufsabri.learn.gadsleaderboard.R
import me.abdelraoufsabri.learn.gadsleaderboard.model.SkillIqTopLearner

@SuppressLint("SetTextI18n")
class SkillIqAdapter(
    learners: List<SkillIqTopLearner> = arrayListOf()
) : RecyclerView.Adapter<SkillIqAdapter.ViewHolder>() {
    var learners = learners
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_top_skill_iq_leader, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(learners[position])
    }

    override fun getItemCount() = learners.count()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(learner: SkillIqTopLearner) {
            with(itemView){
                tvLearnerName.text = learner.name
                tvLearnerDescription.text = "${learner.score} skill IQ Score, ${learner.country}"
            }
        }

    }
}