package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    lateinit var lyj_cardBtn: Button

    lateinit var lyj_totalPrice: TextView
    lateinit var lyj_payBtn: LinearLayout
    lateinit var lyj_payImgBtn: ImageView
    lateinit var lyj_recyclerView: RecyclerView
    lateinit var lyj_adapter: RecyclerViewAdapter
    lateinit var lyj_itemList: ArrayList<ItemData>
private var lyj_totalCount = 0


    // 리스트 추가
    private val fragments = listOf(
        hotCof_Fragment()
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "LYJ KIOSK"



        lyj_goHome = findViewById<ImageButton>(R.id.lyj_goHome)
        lyj_recyclerView = findViewById<RecyclerView>(R.id.lyj_recyclerView)
        lyj_delAllBtn = findViewById<Button>(R.id.lyj_delAllBtn)

        lyj_totalPrice = findViewById(R.id.lyj_totalPrice)
        lyj_payBtn = findViewById<LinearLayout>(R.id.lyj_payBtn)
        lyj_payImgBtn = findViewById<ImageButton>(R.id.lyj_payImgBtn)
        lyj_goHome.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
        }
        lyj_delAllBtn.setOnClickListener {
            clearItemListAll()
            Toast.makeText(applicationContext, "주문이 전체 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        lyj_payBtn.setOnClickListener {
            val dialog = listDialog(lyj_itemList,lyj_totalPrice.text.toString(), lyj_totalCount.toString(),::updateTotalPrice)
            dialog.show(this.supportFragmentManager, "CustomDialog")
//            Toast.makeText(applicationContext, lyj_totalCount.toString(), Toast.LENGTH_SHORT).show()
        }
        lyj_payImgBtn.setOnClickListener {
            val dialog = listDialog(lyj_itemList,lyj_totalPrice.text.toString(),lyj_totalCount.toString(),::updateTotalPrice)
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
            "커피(ICE)",
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
2
        // 초기화
        lyj_itemList = ArrayList()
        lyj_adapter = RecyclerViewAdapter(lyj_itemList,::updateTotalPrice)  // ::updateTotalPrice 함수 참조

        // 어댑터 설정
        lyj_recyclerView.adapter = lyj_adapter
        // 아이템 수직 배치
        lyj_recyclerView.layoutManager = LinearLayoutManager(this)

        updateTotalPrice()



    }

    private fun updateTotalPrice() {
        val totalPrice = lyj_adapter.getTotalPrice()
        // Count 저장
        val totalCount = lyj_adapter.getTotalCount()
        lyj_totalPrice.text = totalPrice.toString()
        lyj_totalCount = totalCount
    }

    // 리스트 전체 삭제
    private fun clearItemListAll() {
        lyj_itemList.clear()
        lyj_adapter.notifyDataSetChanged()
        updateTotalPrice()
    }

    fun addNewItem(itemData: ItemData) {
        // itemList에 새로운 데이터 추가하여 어댑터에 알리기
        /*val newData = ItemData("이름","가격")
        lyj_itemList.add(newData)
        lyj_adapter.notifyItemInserted(lyj_itemList.size - 1)
        lyj_adapter.notifyDataSetChanged()*/

//        val newData = ItemData("이름","가격")

        // ItemData 객체를 전달받아서 리스트에 추가하기
        lyj_itemList.add(itemData)
        lyj_adapter.notifyItemInserted(lyj_itemList.size - 1)
        lyj_adapter.notifyDataSetChanged()

        updateTotalPrice()
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



