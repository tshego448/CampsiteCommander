package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {

    // Initial list with the requested items
    private val gearList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDetails = findViewById<Button>(R.id.btnDetails)
        val tvTotalCount = findViewById<TextView>(R.id.tvTotalCount)
        val tvGearListItems = findViewById<TextView>(R.id.tvGearListItems)

        // List of items we can add
        val availableItems = listOf("Tent", "Torch", "Batteries")
        var nextItemIndex = 0

        // Function to refresh the screen display
        fun updateUI() {
            // Update total count using the loop method
            val total = calculateTotalItems(gearList)
            tvTotalCount.text = total.toString()

            if (gearList.isEmpty()) {
                tvGearListItems.text = "No items selected yet"
            } else {
                // Count occurrences of each item to display quantities
                val itemCounts = mutableMapOf<String, Int>()
                for (item in gearList) {
                    itemCounts[item] = itemCounts.getOrDefault(item, 0) + 1
                }

                // Build the list display string with quantities
                val listDisplay = StringBuilder()
                for ((item, count) in itemCounts) {
                    listDisplay.append("• ").append(item).append(" (Qty: ").append(count).append(")\n")
                }
                tvGearListItems.text = listDisplay.toString().trim()
            }
        }

        // Initialize UI with starting items
        updateUI()

        btnAdd.setOnClickListener {
            // Cycles through adding Tent, Torch, and Batteries
            val itemToAdd = availableItems[nextItemIndex]
            gearList.add(itemToAdd)
            nextItemIndex = (nextItemIndex + 1) % availableItems.size
            updateUI()
        }

        btnDetails.setOnClickListener {
            // Count items to pass to DetailedView
            val tentCount = gearList.count { it == "Tent" }
            val torchCount = gearList.count { it == "Torch" }
            val batteriesCount = gearList.count { it == "Batteries" }

            val intent = Intent(this, DetailedView::class.java).apply {
                putExtra("TENT_COUNT", tentCount)
                putExtra("TORCH_COUNT", torchCount)
                putExtra("BATTERIES_COUNT", batteriesCount)
            }
            startActivity(intent)
        }
    }

    /**
     * Demonstrates using a loop to calculate the total
     */
    private fun calculateTotalItems(items: List<String>): Int {
        var count = 0
        for (item in items) {
            count++
        }
        return count
    }
}
