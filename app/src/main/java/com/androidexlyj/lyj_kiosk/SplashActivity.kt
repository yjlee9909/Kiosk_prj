package com.androidexlyj.lyj_kiosk

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // 일정 시간 지연 이후 실행
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
            // 처음만 실행
            finish()

        }, 2000) // 시간 2초 이후 실행
    }
}