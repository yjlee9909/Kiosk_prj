package com.androidexlyj.lyj_kiosk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TotalListRecyclerViewAdapter (private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<TotalListRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyj_recyMenuNameList: TextView = itemView.findViewById(R.id.lyj_recyMenuNameList)
        val lyj_recyMenuPriceList: TextView = itemView.findViewById(R.id.lyj_recyMenuPriceList)
        val lyj_optionPlusShotList: TextView = itemView.findViewById(R.id.lyj_optionPlusShotList)
        val lyj_optionPlusShotPriceList: TextView = itemView.findViewById(R.id.lyj_optionPlusShotPriceList)
        val lyj_cntList: TextView = itemView.findViewById(R.id.lyj_cntList)
        val lyj_optionHotIceMenuText: TextView = itemView.findViewById(R.id.lyj_optionHotIceMenuText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 아이템 데이터 가져오기
        val item = itemList[position]

        // 뷰홀더에 데이터 설정
        holder.lyj_recyMenuNameList.text = item.name
        holder.lyj_recyMenuPriceList.text = (item.price.toInt() * item.count).toString()
        holder.lyj_cntList.text = item.count.toString()

        holder.lyj_optionPlusShotList.text = item.optShotName.toString()
        holder.lyj_optionPlusShotPriceList.text = (item.optShotPrice.toInt()*item.count).toString()

        holder.lyj_optionHotIceMenuText.text = item.selectedHotIceOption

    }



    fun getTotalPrice(): Int {
        var totalPrice = 0
        for(item in itemList) {
            totalPrice += item.price.toInt()*item.count
            totalPrice += item.optShotPrice.toInt()*item.count
        }
        return totalPrice
    }
    fun getTotalCount(): Int {
        var totalCount = 0
        for (item in itemList) {
            totalCount += item.count
        }
        return totalCount
    }
}