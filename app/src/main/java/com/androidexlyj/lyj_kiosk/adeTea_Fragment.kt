package com.androidexlyj.lyj_kiosk

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class adeTea_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adetea, container, false)
        val linearLayouts = listOf<LinearLayout>(
            view.findViewById(R.id.lyj_lemonade),
            view.findViewById(R.id.lyj_bluelemonade),
            view.findViewById(R.id.lyj_jamong),
            view.findViewById(R.id.lyj_greengrapeTea),
            view.findViewById(R.id.lyj_greentea),
            view.findViewById(R.id.lyj_peachIceTea),
            view.findViewById(R.id.lyj_yuzaTea),
            view.findViewById(R.id.lyj_jamongTea),
            view.findViewById(R.id.lyj_lemonTea)
        )
        // 각 메뉴 리니어 클릭이벤트 선언
        for (linearLayout in linearLayouts) {
            linearLayout.setOnClickListener {
                handleLinearClick(linearLayout)
            }
        }
        return view
    }

    // 해당 리니어가 클릭되었을 때 호출되는 메서드
    // 클릭된 리니어의 ID 가져오기
    private fun handleLinearClick(linearLayout: LinearLayout) {
        val linearLayoutId = linearLayout.id

        // 해당 리니어의 "id_~" 값들 동적으로 가져오기
        // getIdentifier()로 리소스의 ID 반환
        val textId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_text",
            "id",
            requireActivity().packageName
        )
        val priceId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_price",
            "id",
            requireActivity().packageName
        )
        val imageId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_img",
            "id",
            requireActivity().packageName
        )

        var text: String? = null
        var price: Int? = null
        var imageDrawable: Drawable? = null

        // 해당 리니어의 정보 가져오기 (해당 뷰에 접근)
        // 이름
        linearLayout.findViewById<TextView>(textId)?.let {
            text = it.text.toString()
        }
        // Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

        // 가격
        linearLayout.findViewById<TextView>(priceId)?.let {
            price = it.text.toString().toInt()
        }
        // 이미지
        linearLayout.findViewById<ImageView>(imageId)?.let { imageView ->
            imageDrawable = imageView.background
        }

        if (text != null && price != null && imageDrawable != null) {
            // 다이얼로그에 정보 전달
            val dialog = optionDialog(
                resources.getResourceEntryName(linearLayoutId), text!!, price!!, imageDrawable!!
            )
            dialog.show(activity?.supportFragmentManager!!, "CustomDialog")
        }
    }
}