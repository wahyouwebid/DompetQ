package id.pixis.dompetq.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.AdapterLastTransactionBinding
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Converter.currencyIdr
import id.pixis.dompetq.utils.Utils

class TransactionAdapter (
    private val showDetail: (Transactions) -> Unit
) : PagedListAdapter<Transactions, TransactionAdapter.ViewHolder>(DIFF_CALLBACK) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                val type = getItem(position)?.type
                if (type == 0) {
                    tvType.text = "Pemasukan"
                    tvType.setTextColor(Color.parseColor("#66BB6A"))
                    tvAmount.text = getItem(position)?.amount?.let {
                        currencyIdr(it)?.replace(",00","")
                    }
                    tvAmount.setTextColor(Color.parseColor("#66BB6A"))
                } else  {
                    tvType.text = "Pengeluaran"
                    tvType.setTextColor(Color.parseColor("#EF5350"))
                    tvAmount.text = "-${getItem(position)?.amount?.let {
                        currencyIdr(it)?.replace(",00","")
                    }}"
                    tvAmount.setTextColor(Color.parseColor("#EF5350"))
                }
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
            AdapterLastTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterLastTransactionBinding) : RecyclerView.ViewHolder(view.root)
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