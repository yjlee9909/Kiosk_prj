package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.Toast
import androidx.constraintlayout.widget.Placeholder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var lyj_goHome: ImageButton
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    lateinit var lyj_cardBtn: Button
    lateinit var lyj_payBtn: LinearLayout

    // 리스트 추가
    private val fragments = listOf(
        hotCof_Fragment()
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "LYJ KIOSK"

        lyj_goHome = findViewById<ImageButton>(R.id.lyj_goHome)
        lyj_payBtn = findViewById<LinearLayout>(R.id.lyj_payBtn)
        lyj_goHome.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
        }
        lyj_payBtn.setOnClickListener {
            val dialog = payCardDialog()
            dialog.show(this.supportFragmentManager, "CustomDialog")
        }

        // 초기화
        tabLayout = findViewById(R.id.lyj_tabLayout)
        viewPager2 = findViewById(R.id.lyj_viewPager2)
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter
//        // 탭 추가
        val tabs = listOf(
            "커피(HOT)",
            "커피(ICD)",
            "에이드&주스",
            "티(Tea)",
            "음료",
            "디저트"
        )

////        // ViewPager2 어댑터 작성
//        viewPager2.adapter = object : FragmentStateAdapter(this) {
//            // 프래그먼트 저장 배열
//            var fragments = listOf<Fragment>()
//            override fun getItemCount(): Int = fragments.size
//            override fun createFragment(position: Int): Fragment {
//                return when (position) {
//                    0 -> CoffeeHotFragment()
//                    1 -> CoffeeIceFragment()
//                    else -> CoffeeIceFragment()
//                }
//                return fragments.get(position)
//            }
//        }

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // 리스트 목록을 가져와 탭에 보여주기
            tab.text = tabs[position]
        }.attach()
        // attach로 TabLayout, ViewPager 연결


    }

    class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 6

        // Fragment 연결
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> hotCof_Fragment()
                1 -> iceCof_Fragment()
                else -> iceCof_Fragment()
            }
        }

    }



    // 백버튼 막기
    override fun onBackPressed() {
        //        super.onBackPressed()
    }


}



