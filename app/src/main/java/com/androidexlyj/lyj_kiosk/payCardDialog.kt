package com.androidexlyj.lyj_kiosk

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
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
            val mainActivity = activity as MainActivity
            mainActivity.let {
                Toast.makeText(context, "결제중입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(context, "결제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    it.clearItemListAll()
                    val dialog = thxDialog()
                    dialog.show(parentFragmentManager, "CustomDialog")
                    dismiss()


                }, 5000)
            }


/*
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(activity, LoadingActivity::class.java)
                startActivity(intent)
            }, 5000)*/



        }

        return view
    }
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/


}