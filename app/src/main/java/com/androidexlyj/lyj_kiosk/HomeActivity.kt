package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout


class HomeActivity : AppCompatActivity() {

    lateinit var lyj_goOrder: LinearLayout
    lateinit var imgView: ImageView
    var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lyj_goOrder = findViewById<LinearLayout>(R.id.lyj_goOrder)
        imgView = findViewById<ImageView>(R.id.imageView)

        // 메뉴 화면으로 이동
        lyj_goOrder.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 백버튼 막기
    override fun onBackPressed() {
        // super.onBackPressed()
        // 백 버튼 눌렸을 때 첫 번째 누른 시간 기록하고 2.5초 이내에 다시 안눌린다면 시간 갱신
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis()
            return
        }
        // 백 버튼 누른 이후 2.5초 이내에 다시 누르면 앱 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            // 종료
            finishAffinity()
        }
    }
}