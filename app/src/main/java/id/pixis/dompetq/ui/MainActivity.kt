package id.pixis.dompetq.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.R
import id.pixis.dompetq.databinding.ActivityMainBinding
import id.pixis.dompetq.databinding.BottomSheetBinding
import id.pixis.dompetq.ui.bill.BillFragment
import id.pixis.dompetq.ui.bill.add.AddBillActivity
import id.pixis.dompetq.ui.home.HomeFragment
import id.pixis.dompetq.ui.savings.SavingsFragment
import id.pixis.dompetq.ui.transaction.TransactionFragment
import id.pixis.dompetq.ui.transaction.add.AddTransactionActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupFragment(HomeFragment())
        setupTab()
    }

    @SuppressLint("ResourceAsColor")
    private fun setupTab(){
        with(binding){
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home), 0)
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transaction), 1)
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_add), 2)
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_savings), 3)
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_bill), 4)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tabLayout.getTabAt(0)?.icon!!.setColorFilter(
                        resources.getColor(R.color.colorPrimary, theme),
                        PorterDuff.Mode.SRC_IN
                )
            }

            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.icon!!.setColorFilter(
                        resources.getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_IN
                    )
                    when (tab.position) {
                        0 -> setupFragment(HomeFragment())
                        1 -> setupFragment(TransactionFragment())
                        2 -> setupBottomSheet()
                        3 -> setupFragment(SavingsFragment())
                        4 -> setupFragment(BillFragment())
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.icon!!.setColorFilter(
                        resources.getColor(R.color.grey_60),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

            fabAdd.setOnClickListener { setupBottomSheet() }
        }
    }

    private fun setupBottomSheet(){
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            tvTransaction.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, AddTransactionActivity::class.java)
                )
                dialog.dismiss()
            }

            tvBill.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, AddBillActivity::class.java)
                )
                dialog.dismiss()
            }

            tvSavings.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun setupFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, fragment)
        transaction.commit()
    }
}