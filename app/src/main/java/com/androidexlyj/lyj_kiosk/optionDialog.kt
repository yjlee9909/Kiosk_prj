package com.androidexlyj.lyj_kiosk

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class optionDialog(private val id: String, private val text: String, private val price: Int) :
    DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!

    // 클래스의 멤버 변수로 선언하여 클래스 전체에서 참조 가능
    private lateinit var lyj_basic: RadioButton
    private lateinit var lyj_light: RadioButton
    private lateinit var lyj_addShot: RadioButton
    private lateinit var lyj_addTwoShot: RadioButton
    private lateinit var lyj_optionCart: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_option_menu, container, false)
        val lyj_optionMenuName = view.findViewById<TextView>(R.id.lyj_optionMenuName)
        val lyj_optionMenuPrice = view.findViewById<TextView>(R.id.lyj_optionMenuPrice)
        val lyj_optHotOrIce = view.findViewById<LinearLayout>(R.id.lyj_optHotOrIce)
        val lyj_optionDel = view.findViewById<Button>(R.id.lyj_optionDel)
        lyj_optionCart = view.findViewById(R.id.lyj_optionCart)

        val lyj_shotRadio = view.findViewById<RadioGroup>(R.id.lyj_shotRadio)

        lyj_basic = view.findViewById(R.id.lyj_basic)
        lyj_light = view.findViewById(R.id.lyj_light)
        lyj_addShot = view.findViewById(R.id.lyj_addShot)
        lyj_addTwoShot = view.findViewById(R.id.lyj_addTwoShot)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)

        /*Toast.makeText(context,id, Toast.LENGTH_SHORT).show()*/
        Toast.makeText(context, price.toString(), Toast.LENGTH_SHORT).show()

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
            updateOptCartBtnState()
        }
        lyj_light.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = true
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = false
            updateOptCartBtnState()
        }
        lyj_addShot.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = false
            lyj_addShot.isChecked = true
            lyj_addTwoShot.isChecked = false
            updateOptCartBtnState()
        }
        lyj_addTwoShot.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = false
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = true
            updateOptCartBtnState()
        }

        updateOptCartBtnState()



        lyj_optionDel.setOnClickListener {
            dismiss()
        }



        lyj_optionCart.setOnClickListener {
//            Toast.makeText(context,"주문이 담겼습니다.", Toast.LENGTH_SHORT).show()
            val lyj_ItemName = lyj_optionMenuName.text.toString()
            val lyj_ItemPriceText = lyj_optionMenuPrice.text.toString()

            // ItemData에 값 전달
            val lyj_itemData = ItemData(lyj_ItemName, lyj_ItemPriceText)
            val mainActivity = activity as MainActivity
            // 아이템 추가
            mainActivity.addNewItem(lyj_itemData)
            dismiss()
        }

        return view
    }


    private fun updateOptCartBtnState() {
        val isAnyRadBtnSelected =
            lyj_basic.isChecked || lyj_light.isChecked || lyj_addShot.isChecked || lyj_addTwoShot.isChecked
        lyj_optionCart.isEnabled = isAnyRadBtnSelected

        if (isAnyRadBtnSelected) {
            // 라디오 버튼 선택된 경우 주문담기 버튼 활성화
            lyj_optionCart.setBackgroundColor(Color.parseColor("#006400"))
            lyj_optionCart.setTextColor(Color.parseColor("#FFFFFF"))
        } else {

            // 그게 아니라면 비활성화
            lyj_optionCart.setBackgroundResource(R.drawable.stroke_btn)
            lyj_optionCart.setTextColor(Color.parseColor("#006400"))

//            Toast.makeText(context,"옵션을 선택해주세요.",Toast.LENGTH_SHORT).show()
        }
    }


}