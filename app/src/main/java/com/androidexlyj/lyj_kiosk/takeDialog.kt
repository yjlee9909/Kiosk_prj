package com.androidexlyj.lyj_kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class takeDialog(private val totalPrice: String) : DialogFragment() {
    //    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!
    private lateinit var lyj_eatRight: Button
    private lateinit var lyj_takeOut: Button
    private lateinit var lyj_takeNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_take, container, false)
        lyj_eatRight = view.findViewById(R.id.lyj_eatRight)
        lyj_takeOut = view.findViewById(R.id.lyj_takeOut)
        val lyj_takeCancel = view.findViewById<Button>(R.id.lyj_takeCancel)
        lyj_takeNext = view.findViewById(R.id.lyj_takeNext)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)

        // 다음 버튼 디자인 변경
        lyj_takeNext.setBackgroundResource(R.drawable.stroke_btn)
        lyj_takeNext.setTextColor(Color.parseColor("#006400"))


        lyj_eatRight.setOnClickListener {
            selectEatBtn(lyj_eatRight)
        }

        lyj_takeOut.setOnClickListener {
            selectEatBtn(lyj_takeOut)
        }
        lyj_takeCancel.setOnClickListener {
            dismiss()
        }


        lyj_takeNext.setOnClickListener {
//            Toast.makeText(context, totalPrice, Toast.LENGTH_SHORT).show()
            if (!(lyj_takeOut.isSelected) && !(lyj_eatRight.isSelected)) {
                lyj_takeNext.isEnabled = true
                Toast.makeText(context, "장소를 선택해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                lyj_takeNext.isEnabled = false
                val dialog = payCardDialog(totalPrice.toString())
                dialog.show(parentFragmentManager, "CustomDialog")
                dismiss()
            }

        }

        return view
    }

    private fun selectEatBtn(selectBtn: Button) {

            if (selectBtn == lyj_takeOut) {
                // 다음 버튼
                lyj_takeNext.setBackgroundColor(Color.parseColor("#006400"))
                lyj_takeNext.setTextColor(Color.parseColor("#FFFFFF"))

                lyj_takeOut.isSelected = true
                lyj_eatRight.isSelected = false
                // 포장 버튼
                lyj_takeOut.setBackgroundColor(Color.parseColor("#006400"))
                lyj_takeOut.setTextColor(Color.parseColor("#FFFFFF"))

                // 먹고가기 버튼
                lyj_eatRight.setBackgroundResource(R.drawable.stroke_btn)
                lyj_eatRight.setTextColor(Color.parseColor("#006400"))
            } else if (selectBtn == lyj_eatRight) {
                // 다음 버튼
                lyj_takeNext.setBackgroundColor(Color.parseColor("#006400"))
                lyj_takeNext.setTextColor(Color.parseColor("#FFFFFF"))

                lyj_takeOut.isSelected = false
                lyj_eatRight.isSelected = true
                // 포장 버튼
                lyj_eatRight.setBackgroundColor(Color.parseColor("#006400"))
                lyj_eatRight.setTextColor(Color.parseColor("#FFFFFF"))

                // 먹고가기 버튼
                lyj_takeOut.setBackgroundResource(R.drawable.stroke_btn)
                lyj_takeOut.setTextColor(Color.parseColor("#006400"))
            }







    }


}