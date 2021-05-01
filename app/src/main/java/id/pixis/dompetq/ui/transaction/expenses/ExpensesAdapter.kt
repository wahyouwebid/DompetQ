package id.pixis.dompetq.ui.transaction.expenses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.AdapterExpensesBinding
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Converter.currencyIdr
import id.pixis.dompetq.utils.Utils

class ExpensesAdapter (
    private val showDetail: (Transactions) -> Unit
) : PagedListAdapter<Transactions, ExpensesAdapter.ViewHolder>(DIFF_CALLBACK) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                tvCategory.text = getItem(position)?.category
                tvAmount.text = "-${getItem(position)?.amount?.let { 
                    currencyIdr(it)?.replace(",00","") 
                }}"
                tvDate.text = getItem(position)?.date?.let {
                    Converter.dateFormat(
                            it,
                            "yyyyMMdd",
                            "dd MMMM yyyy"
                    )
                }
                getItem(position)?.icon?.let {
                    Utils.getDrawableIdFromFileName(
                        imgThumbnail.context,
                        it
                    )
                }?.let { imgThumbnail.setImageResource(it) }
                root.setOnClickListener { showDetail.invoke(getItem(position)!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterExpensesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterExpensesBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transactions>(){
            override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.category == newItem.category
                )
            }
        }
    }
}