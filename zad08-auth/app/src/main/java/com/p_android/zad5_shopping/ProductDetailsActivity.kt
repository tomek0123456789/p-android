package com.p_android.zad5_shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.p_android.zad5_shopping.data.Product
import com.p_android.zad5_shopping.data.ProductObject
import com.p_android.zad5_shopping.databinding.ActivityItemDetailsActitvityBinding

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityItemDetailsActitvityBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_details_actitvity)
        binding.product = intent.getSerializableExtra("product") as ProductObject?
    }
}