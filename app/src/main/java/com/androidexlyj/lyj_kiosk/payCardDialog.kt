package com.androidexlyj.lyj_kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class payCardDialog(private val getTotalPrice: String) : DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!

    private var totalPrice: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_card, container, false)
        val lyj_cancelBtn = view.findViewById<Button>(R.id.lyj_cancelBtn)
        val lyj_approveBtn = view.findViewById<Button>(R.id.lyj_approveBtn)
        val lyj_totalPriceDialog = view.findViewById<TextView>(R.id.lyj_totalPriceDialog)

        val params: WindowManager.LayoutParams = dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog?.setCancelable(true)

        lyj_totalPriceDialog.text = getTotalPrice


        lyj_cancelBtn.setOnClickListener {
            dismiss()
        }

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