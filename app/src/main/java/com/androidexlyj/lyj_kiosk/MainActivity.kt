package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var lyj_goHome : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "LYJ KIOSK"

        lyj_goHome = findViewById<ImageButton>(R.id.lyj_goHome)
        lyj_goHome.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)

        }

    }
    // 백버튼 막기
    override fun onBackPressed() {
//        super.onBackPressed()
    }
}