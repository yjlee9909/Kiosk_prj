package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class LoadingActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var imgView : ImageView
    var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        button = findViewById<Button>(R.id.lyj_goOrder)
        imgView = findViewById<ImageView>(R.id.imageView)

        button.setOnClickListener {
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