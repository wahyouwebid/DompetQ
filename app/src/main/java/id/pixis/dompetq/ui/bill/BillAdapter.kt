package id.pixis.dompetq.ui.bill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.databinding.AdapterBillBinding
import id.pixis.dompetq.utils.Converter.currencyIdr

class BillAdapter (
    private val showDetail: (Bill) -> Unit
) : PagedListAdapter<Bill, BillAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                tvCategory.text = getItem(position)?.category
                tvAmount.text = getItem(position)?.amount?.let { currencyIdr(it) }
                imgThumbnail.load(getItem(position)?.icon)
                root.setOnClickListener { showDetail.invoke(getItem(position)!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterBillBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bill>(){
            override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.name == newItem.name
                )
            }
        }
    }
}