package id.pixis.dompetq.ui.transaction.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.databinding.AdapterCategoriesBinding
import id.pixis.dompetq.utils.Utils.getDrawableIdFromFileName


class CategoriesAdapter(
        private val showDetail: (Categories) -> Unit
) : PagedListAdapter<Categories, CategoriesAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                getItem(position)?.icon?.let {
                    getDrawableIdFromFileName(
                            imgThumbnail.context,
                            it
                    )
                }?.let { imgThumbnail.setImageResource(it) }
                root.setOnClickListener { showDetail.invoke(getItem(position)!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterCategoriesBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Categories>(){
            override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.name == newItem.name
                )
            }
        }
    }
}