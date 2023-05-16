package com.androidexlyj.lyj_kiosk

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment

class payCardDialog : DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_card, container, false)
        /*val lyj_optionDel = view.findViewById<Button>(R.id.lyj_o*//**//*ptionDel)*/
        val lyj_approveBtn = view.findViewById<Button>(R.id.lyj_approveBtn)

        val params: WindowManager.LayoutParams = dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog?.setCancelable(true)




        /*lyj_optionDel.setOnClickListener {
            dismiss()
        }*/

    lyj_approveBtn.setOnClickListener {
            dismiss()
        }

        return view
    }
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/



}