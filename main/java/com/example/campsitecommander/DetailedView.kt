package com.example.campsitecommander

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get quantities from Intent
        val tentQty = intent.getIntExtra("TENT_COUNT", 0)
        val torchQty = intent.getIntExtra("TORCH_COUNT", 0)
        val batteriesQty = intent.getIntExtra("BATTERIES_COUNT", 0)

        // Find views
        val tvTent = findViewById<TextView>(R.id.itemTent)
        val tvTorch = findViewById<TextView>(R.id.itemTorch)
        val tvBatteries = findViewById<TextView>(R.id.itemBatteries)

        // Update text with quantities
        tvTent.text = "• Tent (Qty: $tentQty) - can accommodate 6 people"
        tvTorch.text = "• Torch (Qty: $torchQty) - requires AA batteries"
        tvBatteries.text = "• Batteries (Qty: $batteriesQty) - inverter that lasts up to 12 hours"

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}