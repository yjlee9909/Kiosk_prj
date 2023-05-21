package com.androidexlyj.lyj_kiosk

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [hotCof_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class hotCof_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hotcof, container, false)
        val lyj_hot_ameri = view.findViewById<LinearLayout>(R.id.lyj_hot_ameri)
        val lyj_hot_ameri_text = view.findViewById<TextView>(R.id.lyj_hot_ameri_text)
        val lyj_hot_ameri_price = view.findViewById<TextView>(R.id.lyj_hot_ameri_price)

        lyj_hot_ameri.setOnClickListener {
            /*Toast.makeText(context, "짧은 토스트 메시지입니다.",Toast.LENGTH_SHORT).show()
            Log.d("click", "hi")*/
            val id = resources.getResourceEntryName(R.id.lyj_hot_ameri)
            val text = lyj_hot_ameri_text.text.toString()
            val price = lyj_hot_ameri_price.text.toString().toInt()
            val dialog = optionDialog(id, text, price)
            dialog.show(activity?.supportFragmentManager!!, "CustomDialog")

        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment hotCof_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            hotCof_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}