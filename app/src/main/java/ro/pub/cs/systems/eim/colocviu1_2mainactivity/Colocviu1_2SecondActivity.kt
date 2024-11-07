package ro.pub.cs.systems.eim.colocviu1_2mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class Colocviu1_2SecondActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val allTerms = intent.getStringExtra("ALL_TERMS")
        val result = calculateSum(allTerms)

        val resultIntent = Intent().apply {
            putExtra("RESULT", result)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun calculateSum(terms: String?): Int {
        if (terms.isNullOrEmpty()) return 0
        return terms.split(" + ").sumOf { it.toIntOrNull() ?: 0 }
    }
}