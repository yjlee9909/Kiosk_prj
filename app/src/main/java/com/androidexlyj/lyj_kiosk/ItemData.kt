package com.androidexlyj.lyj_kiosk

data class ItemData(val id: String, val name: String, val price: String, var count: Int,
                    val selectedHotIceOption: String,val optShotName: String
,val optShotPrice : Int) {
}