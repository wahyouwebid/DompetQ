package id.pixis.dompetq.ui.transaction.income

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.pixis.dompetq.R
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.AdapterBillBinding
import id.pixis.dompetq.databinding.AdapterIncomeBinding
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Converter.currencyIdr

class IncomeAdapter (
    private val showDetail: (Transactions) -> Unit
) : PagedListAdapter<Transactions, IncomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                tvCategory.text = getItem(position)?.category
                tvAmount.text = getItem(position)?.amount?.let { currencyIdr(it) }
                tvDate.text = getItem(position)?.date
                imgThumbnail.load(R.drawable.icon_thumbnail)
                root.setOnClickListener { showDetail.invoke(getItem(position)!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterIncomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterIncomeBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transactions>(){
            override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.name == newItem.name
                )
            }
        }
    }
}