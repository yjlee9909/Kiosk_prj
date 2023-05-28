package com.androidexlyj.lyj_kiosk

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [iceCof_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class iceCof_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_icecof, container, false)

        val linearLayouts = listOf<LinearLayout>(
            view.findViewById(R.id.lyj_ice_ameri),
            view.findViewById(R.id.lyj_ice_latte),
            view.findViewById(R.id.lyj_ice_Vanilla_latte),
            view.findViewById(R.id.lyj_ice_caramelMac),
            view.findViewById(R.id.lyj_ice_cafeMocha),
            view.findViewById(R.id.lyj_ice_cappuccino),
            view.findViewById(R.id.lyj_ice_tiramisuLatte),
            view.findViewById(R.id.lyj_ice_coldbrew),
            view.findViewById(R.id.lyj_ice_coldbrewLatte)
        )

        for (linearLayout in linearLayouts) {
            linearLayout.setOnClickListener {
                handleLinearClick(linearLayout)
            }
        }


        return view
    }

    private fun handleLinearClick(linearLayout: LinearLayout) {
        val linearLayoutId = linearLayout.id
        // 해당 리니어의 id_~ 값들 가져오기
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

        // 해당 리니어의 정보 가져오기
        linearLayout.findViewById<TextView>(textId)?.let {
            text = it.text.toString()
        }
        linearLayout.findViewById<TextView>(priceId)?.let {
            price = it.text.toString().toInt()
        }
        linearLayout.findViewById<ImageView>(imageId)?.let { imageView ->
            imageDrawable = imageView.background
        }


        if (text != null && price != null && imageDrawable != null) {
            // 다이얼로그에 정보 전달
            val dialog = optionDialog(
                resources.getResourceEntryName(linearLayoutId),
                text!!, price!!, imageDrawable!!
            )
            dialog.show(activity?.supportFragmentManager!!, "CustomDialog")
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            hotCof_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}