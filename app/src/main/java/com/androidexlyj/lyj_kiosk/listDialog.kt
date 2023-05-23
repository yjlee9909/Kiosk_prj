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
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class listDialog (private val totalPrice: String, private val totalCount: String) : DialogFragment() {
    //    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!
    private lateinit var lyj_listCancel: Button
    private lateinit var lyj_listNext: Button
    private lateinit var lyj_listTotalPrice: TextView
    private lateinit var lyj_listTotalCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_list, container, false)
        lyj_listCancel = view.findViewById(R.id.lyj_listCancel)
        lyj_listNext = view.findViewById(R.id.lyj_listNext)
        lyj_listTotalPrice = view.findViewById(R.id.lyj_listTotalPrice)
        lyj_listTotalCount = view.findViewById(R.id.lyj_listTotalCount)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)

        // 가격과 갯수 값 넣기
        lyj_listTotalPrice.text = totalPrice
        lyj_listTotalCount.text = totalCount

        lyj_listCancel.setOnClickListener {
            dismiss()
        }


        lyj_listNext.setOnClickListener {
//            Toast.makeText(context, totalPrice, Toast.LENGTH_SHORT).show()
            val dialog = takeDialog(totalPrice.toString())
            dialog.show(parentFragmentManager, "CustomDialog")
            dismiss()
        }

        return view
    }



}