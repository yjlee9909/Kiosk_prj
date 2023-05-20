package com.androidexlyj.lyj_kiosk

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class optionDialog(private val id: String, private val text: String, private val price: Int) : DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_option_menu, container, false)
        val lyj_optionMenuName = view.findViewById<TextView>(R.id.lyj_optionMenuName)
        val lyj_optionMenuPrice= view.findViewById<TextView>(R.id.lyj_optionMenuPrice)
        val lyj_optHotOrIce = view.findViewById<LinearLayout>(R.id.lyj_optHotOrIce)
        val lyj_optionDel = view.findViewById<Button>(R.id.lyj_optionDel)
        val lyj_optionCart = view.findViewById<Button>(R.id.lyj_optionCart)

    val lyj_basic = view.findViewById<RadioButton>(R.id.lyj_basic)
    val lyj_light = view.findViewById<RadioButton>(R.id.lyj_light)
    val lyj_addShot = view.findViewById<RadioButton>(R.id.lyj_addShot)
    val lyj_addTwoShot = view.findViewById<RadioButton>(R.id.lyj_addTwoShot)

        val params: WindowManager.LayoutParams = dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)

        /*Toast.makeText(context,id, Toast.LENGTH_SHORT).show()*/
        Toast.makeText(context,price.toString(), Toast.LENGTH_SHORT).show()

        // 상품명 넣기
        lyj_optionMenuName.text = text
        lyj_optionMenuPrice.text = price.toString()


        // 탭별로 HOT / ICE 보여주기
        if (id == "lyj_hot_ameri") {
            lyj_optHotOrIce.visibility = View.GONE
        } else {
            lyj_optHotOrIce.visibility = View.VISIBLE
        }

        // 라디오버튼 하나만 선택가능하도록
        lyj_basic.setOnClickListener {
            lyj_basic.isChecked = true
            lyj_light.isChecked = false
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = false
        }
    lyj_light.setOnClickListener {
        lyj_basic.isChecked = false
        lyj_light.isChecked = true
        lyj_addShot.isChecked = false
        lyj_addTwoShot.isChecked = false
    }
    lyj_addShot.setOnClickListener {
        lyj_basic.isChecked = false
        lyj_light.isChecked = false
        lyj_addShot.isChecked = true
        lyj_addTwoShot.isChecked = false
    }
    lyj_addTwoShot.setOnClickListener {
        lyj_basic.isChecked = false
        lyj_light.isChecked = false
        lyj_addShot.isChecked = false
        lyj_addTwoShot.isChecked = true
    }


        lyj_optionDel.setOnClickListener {
            dismiss()
        }

        lyj_optionCart.setOnClickListener {
//            Toast.makeText(context,"주문이 담겼습니다.", Toast.LENGTH_SHORT).show()
            val mainActivity = activity as MainActivity
            mainActivity.addNewItem()
            dismiss()
        }

        return view
    }
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/



}