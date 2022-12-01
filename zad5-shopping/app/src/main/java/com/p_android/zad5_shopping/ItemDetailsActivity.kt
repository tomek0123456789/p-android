package com.p_android.zad5_shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.p_android.zad5_shopping.databinding.ActivityItemDetailsActitvityBinding

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityItemDetailsActitvityBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_details_actitvity)
        binding.item = intent.getSerializableExtra("item") as Item?
    }
}