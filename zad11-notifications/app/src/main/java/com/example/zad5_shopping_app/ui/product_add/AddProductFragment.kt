package com.example.zad5_shopping_app.ui.product_add

import android.app.NotificationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.MenuHost
import androidx.fragment.app.activityViewModels
import com.example.zad5_shopping_app.R
import com.example.zad5_shopping_app.data.Product
import com.example.zad5_shopping_app.databinding.FragmentProductAddBinding
import com.example.zad5_shopping_app.databinding.FragmentProductDetailsBinding
import com.example.zad5_shopping_app.ui.shopping_cart.ShoppingCartViewModel

class AddProductFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)

        binding.addProductButton.setOnClickListener {
            val notificationBuilder = NotificationCompat.Builder(requireContext(), PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setContentTitle("New product")
                .setContentText("You added a new product!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(requireContext())) {
                notify(1, notificationBuilder.build())
            }
        }
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }
}