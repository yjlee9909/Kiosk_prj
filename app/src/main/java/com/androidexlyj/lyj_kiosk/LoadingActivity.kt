package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class LoadingActivity : AppCompatActivity() {

    lateinit var lyj_takeOutBtn: Button
    lateinit var lyj_eatRightBtn: Button
    lateinit var imgView: ImageView
    var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        lyj_takeOutBtn = findViewById<Button>(R.id.lyj_takeOut)
        lyj_eatRightBtn = findViewById<Button>(R.id.lyj_eatRight)
        imgView = findViewById<ImageView>(R.id.imageView)

        lyj_takeOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        lyj_eatRightBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 백버튼 막기
    override fun onBackPressed() {
//        super.onBackPressed()
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            // 종료
            finishAffinity()
        }
    }
}