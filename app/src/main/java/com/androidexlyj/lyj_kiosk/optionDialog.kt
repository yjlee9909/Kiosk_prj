package com.androidexlyj.lyj_kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class optionDialog(private val id: String, private val text: String, private val price: Int, private val imageDrawable: Drawable) :
    DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!

    // 클래스의 멤버 변수로 선언하여 클래스 전체에서 참조 가능
    private lateinit var lyj_basic: RadioButton
    private lateinit var lyj_light: RadioButton
    private lateinit var lyj_addShot: RadioButton
    private lateinit var lyj_addTwoShot: RadioButton
    private lateinit var lyj_optionCart: Button

    private lateinit var lyj_optHot: RadioButton
    private lateinit var lyj_optIce: RadioButton


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_option_menu, container, false)
        val lyj_optionMenuName = view.findViewById<TextView>(R.id.lyj_optionMenuName)
        val lyj_optionMenuPrice = view.findViewById<TextView>(R.id.lyj_optionMenuPrice)
        val lyj_optHotOrIce = view.findViewById<LinearLayout>(R.id.lyj_optHotOrIce)
        val lyj_shotOptionMenu = view.findViewById<LinearLayout>(R.id.lyj_shotOptionMenu)
        val lyj_optionDel = view.findViewById<Button>(R.id.lyj_optionDel)
        val lyj_optionMenuImg = view.findViewById<ImageView>(R.id.lyj_optionMenuImg)



        lyj_optHot = view.findViewById(R.id.lyj_optHot)
        lyj_optIce = view.findViewById(R.id.lyj_optIce)
        lyj_optionCart = view.findViewById(R.id.lyj_optionCart)

        var lyj_plusOptShot = ""
        var lyj_plusOptShotPrice = 0

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
        dialog?.setCancelable(false)

        /*Toast.makeText(context,id, Toast.LENGTH_SHORT).show()*/
//        Toast.makeText(context, price.toString(), Toast.LENGTH_SHORT).show()

        // 상품명 넣기
        lyj_optionMenuName.text = text
        lyj_optionMenuPrice.text = price.toString()
        lyj_optionMenuImg.background = imageDrawable



        // 탭별로 HOT / ICE 보여주기
        /*if (id.contains("hot")||id.contains("ice")) {
            lyj_optHotOrIce.visibility = View.GONE
        } else {
            lyj_optHotOrIce.visibility = View.VISIBLE
        }*/
        if (id.contains("hot")) {
            lyj_optHot.isEnabled = false
            lyj_optIce.isEnabled = false
            lyj_optHot.isChecked = true
            lyj_optHot.setTextColor(Color.RED)
        } else if (id.contains("ice")) {
            lyj_optHot.isEnabled = false
            lyj_optIce.isEnabled = false
            lyj_optIce.isChecked = true
            lyj_optIce.setTextColor(Color.RED)
        } else if (id.contains("dessert")) {
            // dessert 인 경우 옵션 메뉴 안보이게
            lyj_optHotOrIce.visibility = View.GONE
            lyj_shotOptionMenu.visibility = View.GONE
        }

        // hot/ice 버튼을 2번째로 선택한 경우에도 lyj_optionCart 버튼 활성화되도록
        lyj_optHot.setOnClickListener {
            updateOptCartBtnState()
        }
        lyj_optIce.setOnClickListener {
            updateOptCartBtnState()
        }

        // 라디오버튼 하나만 선택가능하도록
        lyj_basic.setOnClickListener {
            lyj_basic.isChecked = true
            lyj_light.isChecked = false
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = false

            val lyj_optShotName = view.findViewById<TextView>(R.id.lyj_basic)
            val lyj_optShotPrice = view.findViewById<TextView>(R.id.lyj_basicWon)

            // 해당 text 정보 뽑아서 전달하기
            lyj_plusOptShot = lyj_optShotName.text.toString()
            lyj_plusOptShotPrice = lyj_optShotPrice.text.toString().toInt()

            updateOptCartBtnState()
        }
        lyj_light.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = true
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = false

            val lyj_optShotName = view.findViewById<TextView>(R.id.lyj_light)
            val lyj_optShotPrice = view.findViewById<TextView>(R.id.lyj_lightWon)

            // 해당 text 정보 뽑아서 전달하기
            lyj_plusOptShot = lyj_optShotName.text.toString()
            lyj_plusOptShotPrice = lyj_optShotPrice.text.toString().toInt()

            updateOptCartBtnState()
        }
        lyj_addShot.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = false
            lyj_addShot.isChecked = true
            lyj_addTwoShot.isChecked = false

            val lyj_optShotName = view.findViewById<TextView>(R.id.lyj_addShot)
            val lyj_optShotPrice = view.findViewById<TextView>(R.id.lyj_addShotWon)

            // 해당 text 정보 뽑아서 전달하기
            lyj_plusOptShot = lyj_optShotName.text.toString()
            lyj_plusOptShotPrice = lyj_optShotPrice.text.toString().toInt()
            updateOptCartBtnState()
        }
        lyj_addTwoShot.setOnClickListener {
            lyj_basic.isChecked = false
            lyj_light.isChecked = false
            lyj_addShot.isChecked = false
            lyj_addTwoShot.isChecked = true

            val lyj_optShotName = view.findViewById<TextView>(R.id.lyj_addTwoShot)
            val lyj_optShotPrice = view.findViewById<TextView>(R.id.lyj_addTwoShotWon)

            // 해당 text 정보 뽑아서 전달하기
            lyj_plusOptShot = lyj_optShotName.text.toString()
            lyj_plusOptShotPrice = lyj_optShotPrice.text.toString().toInt()

            updateOptCartBtnState()
        }

        updateOptCartBtnState()



        lyj_optionDel.setOnClickListener {

            dismiss()
        }



        lyj_optionCart.setOnClickListener {
//            Toast.makeText(context,lyj_plusOptShot, Toast.LENGTH_SHORT).show()
            val lyj_ItemName = lyj_optionMenuName.text.toString()
            val lyj_ItemPriceText = lyj_optionMenuPrice.text.toString()
            val lyj_ItemCnt = "1".toInt()
            val selectedHotIceOption: String

            // 리사이클러뷰로 옵션 문자열 넘기기
            selectedHotIceOption = if (lyj_optHot.isChecked) "뜨거운 (HOT)"
            else if (lyj_optIce.isChecked) "차가운 (ICE)" else ""



            // ItemData에 값 전달
            val lyj_itemData = ItemData(id, lyj_ItemName, lyj_ItemPriceText, lyj_ItemCnt, selectedHotIceOption, lyj_plusOptShot, lyj_plusOptShotPrice)
            val mainActivity = activity as MainActivity
            // 아이템 추가
            mainActivity.addNewItem(lyj_itemData)
            dismiss()
        }

        return view
    }


    private fun updateOptCartBtnState() {

        // dessert인 경우 버튼 활성화
        if (id.contains("dessert")) {
            lyj_optionCart.isEnabled = true
            lyj_optionCart.setBackgroundColor(Color.parseColor("#006400"))
            lyj_optionCart.setTextColor(Color.parseColor("#FFFFFF"))
            return
        }


        // 라디오 버튼 선택 여부로 버튼 활성화
        val isAnyHotIceSelected = listOf(lyj_optHot, lyj_optIce)
            .any { it.isChecked }
        val isAnyRadBtnSelected = listOf(lyj_basic, lyj_light, lyj_addShot, lyj_addTwoShot)
            .any { it.isChecked }

        val lyj_isBothSelected = (isAnyRadBtnSelected && isAnyHotIceSelected)

        lyj_optionCart.isEnabled = lyj_isBothSelected

        if (lyj_isBothSelected) {
            // 라디오 버튼 선택된 경우 주문담기 버튼 활성화
            lyj_optionCart.setBackgroundColor(Color.parseColor("#006400"))
            lyj_optionCart.setTextColor(Color.parseColor("#FFFFFF"))

        }  else {
            // 그게 아니라면 비활성화
            lyj_optionCart.setBackgroundResource(R.drawable.stroke_btn)
            lyj_optionCart.setTextColor(Color.parseColor("#006400"))
        }
    }


}