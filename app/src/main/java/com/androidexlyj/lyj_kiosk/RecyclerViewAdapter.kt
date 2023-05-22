package com.androidexlyj.lyj_kiosk

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerViewAdapter(private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.lyj_recyMenuName)
        val priceTextView: TextView = itemView.findViewById(R.id.lyj_recyMenuPrice)
        val lyj_optionPlusShot: TextView = itemView.findViewById(R.id.lyj_optionPlusShot)
        val lyj_optionPlusShotPrice: TextView = itemView.findViewById(R.id.lyj_optionPlusShotPrice)

        val lyj_cnt: TextView = itemView.findViewById(R.id.lyj_cnt)
        val lyj_recyMenuDel: ImageButton = itemView.findViewById(R.id.lyj_recyMenuDel)
        val lyj_minusBtn: ImageButton = itemView.findViewById(R.id.lyj_minusBtn)
        val lyj_plusBtn: ImageButton = itemView.findViewById(R.id.lyj_plusBtn)

        init {
            // 삭제 버튼 클릭시 각 해당 리스트 삭제
            lyj_recyMenuDel.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // MainActivity 접근
                    (itemView.context as MainActivity).lyj_itemList.removeAt(position)
                    (itemView.context as MainActivity).lyj_adapter.notifyItemRemoved(position)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 아이템 데이터 가져오기
        val item = itemList[position]

        // 뷰홀더에 데이터 설정
         holder.nameTextView.text = item.name
        holder.priceTextView.text = (item.price.toInt() * item.count).toString()
        holder.lyj_cnt.text = item.count.toString()

        holder.lyj_optionPlusShot.text = item.optShotName.toString()
        holder.lyj_optionPlusShotPrice.text = (item.optShotPrice.toInt()*item.count).toString()


        holder.lyj_minusBtn.setOnClickListener {
            if (item.count > 1) {
                item.count--
                holder.lyj_cnt.text = item.count.toString()
                holder.priceTextView.text = (item.price.toInt() * item.count).toString()
                holder.lyj_optionPlusShotPrice.text = (item.optShotPrice.toInt()*item.count).toString()
            }
        }

        holder.lyj_plusBtn.setOnClickListener {
            item.count++
            holder.lyj_cnt.text = item.count.toString()
            holder.priceTextView.text = (item.price.toInt() * item.count).toString()
            holder.lyj_optionPlusShotPrice.text = (item.optShotPrice.toInt()*item.count).toString()
        }
    }
}
