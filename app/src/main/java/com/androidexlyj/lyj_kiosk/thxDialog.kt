package com.androidexlyj.lyj_kiosk

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class thxDialog() : DialogFragment() {
//    private var _binding: DialogLayoutBinding? = null
//    private val binding get() = _binding!!
    private var countTime = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_thx, container, false)
        val lyj_countTime = view.findViewById<TextView>(R.id.lyj_countTime)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.setCancelable(true)
        // 다이얼로그 바깥 화면을 눌러도 종료되지 않도록 설정
        dialog?.setCancelable(false)

        val countdownHandler = Handler(Looper.getMainLooper())
        // 1초마다 실행 (총 5초에서 1초씩 감소시키기)
        val countdownRunnable = object : Runnable {
            override fun run() {
                if (countTime > 0) {
                    lyj_countTime.text = countTime.toString()
                    countTime--
                    countdownHandler.postDelayed(this, 1000)
                } else {
                    // 0초
                    countdownHandler.removeCallbacks(this)
                    val intent = Intent(context, HomeActivity::class.java)
                    startActivity(intent)
                    dismiss()
                }
            }
        }
        countdownHandler.postDelayed(countdownRunnable,1000)



        return view
    }
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/


}