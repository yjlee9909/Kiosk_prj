package com.androidexlyj.lyj_kiosk

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.lyj_recyMenuName)
        val priceTextView: TextView = itemView.findViewById(R.id.lyj_recyMenuPrice)
        val lyj_recyMenuDel: ImageButton = itemView.findViewById(R.id.lyj_recyMenuDel)

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
        val item = itemList[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price
    }
}