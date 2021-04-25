package id.pixis.dompetq.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Parcelize
@Entity
data class Bill(
    @PrimaryKey
    val id : String,
    val name : String,
    val totalBill : String,
    val dueDate : String
) : Parcelable
