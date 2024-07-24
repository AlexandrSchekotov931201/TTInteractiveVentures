package tt.interactive.ventures.ui.chartscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import tt.interactive.ventures.databinding.PointItemBinding
import tt.interactive.ventures.ui.chartscreen.model.PointItem

class PointsAdapter :
    ListAdapter<PointItem, PointsAdapter.PointViewHolder>(PointsDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        return PointViewHolder(parent.viewBindingBuilder(PointItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PointViewHolder(private val binding: PointItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PointItem) {
            binding.tvX.text = item.x.toString()
            binding.tvY.text = item.y.toString()
        }
    }

    private fun <VB : ViewBinding> ViewGroup.viewBindingBuilder(
        inflater: (LayoutInflater, ViewGroup, Boolean) -> VB
    ): VB {
        return inflater.invoke(LayoutInflater.from(this.context), this, false)
    }

    private class PointsDiffUtilItemCallback : DiffUtil.ItemCallback<PointItem>() {
        override fun areItemsTheSame(oldItem: PointItem, newItem: PointItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: PointItem, newItem: PointItem): Boolean {
            return oldItem.x == newItem.x &&
                    oldItem.y == newItem.y
        }

    }

}