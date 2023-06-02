package com.androidexlyj.lyj_kiosk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 전체 총 주문 목록 보여주는 코드
class TotalListRecyclerViewAdapter (private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<TotalListRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyj_recyMenuNameList: TextView = itemView.findViewById(R.id.lyj_recyMenuNameList)
        val lyj_recyMenuPriceList: TextView = itemView.findViewById(R.id.lyj_recyMenuPriceList)
        val lyj_optionPlusShotList: TextView = itemView.findViewById(R.id.lyj_optionPlusShotList)
        val lyj_optionPlusShotPriceList: TextView = itemView.findViewById(R.id.lyj_optionPlusShotPriceList)
        val lyj_cntList: TextView = itemView.findViewById(R.id.lyj_cntList)
        val lyj_optionHotIceMenuText: TextView = itemView.findViewById(R.id.lyj_optionHotIceMenuText)
        val lyj_totalRecyShotLinear: LinearLayout = itemView.findViewById(R.id.lyj_totalRecyShotLinear)
        val lyj_totalRecyHotIceLinear: LinearLayout = itemView.findViewById(R.id.lyj_totalRecyHotIceLinear)
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
        holder.lyj_optionPlusShotList.text = item.optShotName
        holder.lyj_optionPlusShotPriceList.text = (item.optShotPrice * item.count).toString()
        holder.lyj_optionHotIceMenuText.text = item.selectedHotIceOption

        // 아이디가 디저트인경우 해당 옵션 리니어 보이지 않도록
        if (item.id.contains("dessert")) {
            holder.lyj_totalRecyShotLinear.visibility = View.GONE
            holder.lyj_totalRecyHotIceLinear.visibility = View.GONE

        } else {
            holder.lyj_totalRecyShotLinear.visibility = View.VISIBLE
            holder.lyj_totalRecyHotIceLinear.visibility = View.VISIBLE
        }
    }
}