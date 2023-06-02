package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var lyj_goHome: ImageButton
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var lyj_delAllBtn: Button
    lateinit var lyj_countDownTime: TextView
    lateinit var lyj_totalPrice: TextView
    lateinit var lyj_payBtn: LinearLayout
    lateinit var lyj_payImgBtn: ImageView
    lateinit var lyj_recyclerView: RecyclerView
    lateinit var lyj_adapter: RecyclerViewAdapter
    lateinit var lyj_itemList: ArrayList<ItemData>
    private var lyj_totalCount = 0  // 총 개수 표시할 변수

    private lateinit var countDownTimer: CountDownTimer
    private var isCountDownRunning = false
    private val COUNTDOWN_TIME = 300000 // 300초를 밀리초로 표현 300000
    private val COUNTDOWN_INTERVAL = 1000 // 카운트다운 간격을 1초로 설정
    private var initialCountDownTime: Long = COUNTDOWN_TIME.toLong()
    private var isPaused = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "LYJ KIOSK"

        lyj_goHome = findViewById<ImageButton>(R.id.lyj_goHome)
        lyj_recyclerView = findViewById<RecyclerView>(R.id.lyj_recyclerView)
        lyj_delAllBtn = findViewById<Button>(R.id.lyj_delAllBtn)
        lyj_countDownTime = findViewById<TextView>(R.id.lyj_countDownTime)

        lyj_totalPrice = findViewById(R.id.lyj_totalPrice)
        lyj_payBtn = findViewById<LinearLayout>(R.id.lyj_payBtn)
        lyj_payImgBtn = findViewById<ImageButton>(R.id.lyj_payImgBtn)

        lyj_goHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        lyj_delAllBtn.setOnClickListener {
            clearItemListAll()
            Toast.makeText(applicationContext, "주문이 전체 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        lyj_payBtn.setOnClickListener {
            checkListAndHandleButton()
        }
        lyj_payImgBtn.setOnClickListener {
            checkListAndHandleButton()
        }

        // 초기화
        tabLayout = findViewById(R.id.lyj_tabLayout)
        viewPager2 = findViewById(R.id.lyj_viewPager2)
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter
        // 탭 메뉴 추가
        val tabs = listOf(
            "커피(HOT)",
            "커피(ICE)",
            "에이드&티",
            "음료",
            "디저트"
        )

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // 리스트 목록을 가져와 탭에 보여주기
            tab.text = tabs[position]
        }.attach()
        // attach로 TabLayout, ViewPager 연결

        // 초기화
        lyj_itemList = ArrayList()
        // 리사이클러뷰 데이터 표시
        lyj_adapter = RecyclerViewAdapter(
            lyj_itemList,
            ::updateTotalPrice
        )  // ::updateTotalPrice 함수 참조 - 총 가격 업데이트

        // 어댑터 설정
        lyj_recyclerView.adapter = lyj_adapter
        // 아이템 수직 배치
        lyj_recyclerView.layoutManager = LinearLayoutManager(this)
        updateTotalPrice()
    }

    private fun checkListAndHandleButton() {
        if (lyj_itemList.isEmpty()) {
            Toast.makeText(applicationContext, "메뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show()
        } else {
            // 리스트가 존재한다면 아이템 정보 넘기기
            val dialog = listDialog(
                lyj_itemList,
                lyj_totalPrice.text.toString(),
                lyj_totalCount.toString()
            )
            dialog.show(this.supportFragmentManager, "CustomDialog")
        }
        // lyj_itemList 확인
        /*for (itemData in lyj_itemList) {
            // itemData에 대한 처리
            // 예: Log 출력
            Log.d("listcheck", "Item: ${itemData.name}, Price: ${itemData.price}")
        }*/
    }


    // 총 가격 업데이트
    fun updateTotalPrice() {
        val totalPrice = lyj_adapter.getTotalPrice()
        // Count 저장
        val totalCount = lyj_adapter.getTotalCount()
        lyj_totalPrice.text = totalPrice.toString()
        lyj_totalCount = totalCount
    }

    // 리스트 전체 삭제
    fun clearItemListAll() {
        lyj_itemList.clear()
        lyj_adapter.notifyDataSetChanged()
        updateTotalPrice()
        resetCountDown()
    }

    // 아이템 추가
    fun addNewItem(itemData: ItemData) {
        // ItemData 객체를 전달받아서 리스트에 추가하기
        lyj_itemList.add(itemData)
        lyj_adapter.notifyItemInserted(lyj_itemList.size - 1)
        lyj_adapter.notifyDataSetChanged()
        updateTotalPrice()
        resetCountDown()
    }

    private fun resetCountDown() {
        stopCountDown()
        startCountDown()
    }

    override fun onResume() {
        super.onResume()
        if (!isPaused) {
            startCountDown()
        }
    }

    // 일시정지
    override fun onPause() {
        super.onPause()
        isPaused = true
        stopCountDown()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCountDown()
    }

    // 현재 카운트다운 중일때 중지
    private fun stopCountDown() {
        if (isCountDownRunning) {
            countDownTimer.cancel()
        }
        // 카운트다운 중지
        isCountDownRunning = false
    }

    // 카운트다운 시작
    private fun startCountDown() {
        // 카운트다운 시간 초기화
        initialCountDownTime = COUNTDOWN_TIME.toLong()
        countDownTimer =
            object : CountDownTimer(COUNTDOWN_TIME.toLong(), COUNTDOWN_INTERVAL.toLong()) {
                override fun onTick(millisUntilFinished: Long) {
                    val second = millisUntilFinished / 1000
                    lyj_countDownTime.text = second.toString()
                    initialCountDownTime = millisUntilFinished // 카운트 시간 업데이트
                }
                // 카운트다운 종료
                override fun onFinish() {
                    clearItemListAll()
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "주문 시간이 만료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        countDownTimer.start()
        isCountDownRunning = true
    }

    class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 5

        // Fragment 연결
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> hotCof_Fragment()
                1 -> iceCof_Fragment()
                2 -> adeTea_Fragment()
                3 -> drink_Fragment()
                4 -> dessert_Fragment()
                else -> iceCof_Fragment()
            }
        }
    }


    // 백버튼 막기
    override fun onBackPressed() {
        //        super.onBackPressed()
    }


}



