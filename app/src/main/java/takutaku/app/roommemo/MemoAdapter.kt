package takutaku.app.roommemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import takutaku.app.roommemo.databinding.MemoItemBinding

class MemoAdapter(private val onClickListener: OnClickListener): ListAdapter<Memo, MemoViewHolder>(diffUtilItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = getItem(position)
        holder.bind(memo)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(memo)
        }
    }
}

class MemoViewHolder(
    private val binding: MemoItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(memo: Memo) {
        binding.memoTextView.text = memo.content
    }
}

private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Memo>() {
    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnClickListener(val clickListener: (memo: Memo) -> Unit) {
    fun onClick(memo: Memo) = clickListener(memo)
}