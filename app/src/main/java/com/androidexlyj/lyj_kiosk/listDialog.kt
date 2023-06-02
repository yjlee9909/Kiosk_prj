package com.androidexlyj.lyj_kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 전체 총 주문 리스트 보여주는 다이얼로그
class listDialog(
    private val itemList: ArrayList<ItemData>,
    private val totalPrice: String,
    private val totalCount: String
) : DialogFragment() {
    private lateinit var lyj_listCancel: Button
    private lateinit var lyj_listNext: Button
    private lateinit var lyj_listTotalPrice: TextView
    private lateinit var lyj_listTotalCount: TextView
    private lateinit var lyj_recyclerViewItemResult: RecyclerView
    private lateinit var lyj_adapter: TotalListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_list, container, false)
        lyj_listCancel = view.findViewById(R.id.lyj_listCancel)
        lyj_listNext = view.findViewById(R.id.lyj_listNext)
        lyj_listTotalPrice = view.findViewById(R.id.lyj_listTotalPrice)
        lyj_listTotalCount = view.findViewById(R.id.lyj_listTotalCount)

        // 다른 형태의 리사이클러뷰
        lyj_recyclerViewItemResult = view.findViewById(R.id.lyj_recyclerViewItemResult)
        lyj_adapter = TotalListRecyclerViewAdapter(itemList)

        // 리사이클러뷰 아이템 새로 배치
        val layoutManager = LinearLayoutManager(context)
        lyj_recyclerViewItemResult.layoutManager = layoutManager
        lyj_recyclerViewItemResult.adapter = lyj_adapter

        // 다이얼로그 속성 설정
        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)    // 다이얼로그 외부 터치시 닫히지 않도록 설정

        // 가격과 갯수 값 넣기
        lyj_listTotalPrice.text = totalPrice
        lyj_listTotalCount.text = totalCount

        lyj_listCancel.setOnClickListener {
            // 리스트가 비어있지 않다면 MainActivity에 존재하는 버튼 lyj_payBtn, lyj_payImgBtn 다시 클릭 가능
            if (itemList.isNotEmpty()) {
                (activity as? MainActivity)?.lyj_payBtn?.isEnabled = true
                (activity as? MainActivity)?.lyj_payImgBtn?.isEnabled = true
            }
            dismiss()
        }
        lyj_listNext.setOnClickListener {
            val dialog = takeDialog(totalPrice)
            dialog.show(parentFragmentManager, "CustomDialog")
            dismiss()
        }
        return view
    }
}