package ro.pub.cs.systems.eim.colocviu1_2mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.pub.cs.systems.eim.colocviu1_2mainactivity.ui.theme.Colocviu1_2MainActivityTheme

class Colocviu1_2MainActivity : ComponentActivity() {

    private lateinit var nextTermEditText: EditText
    private lateinit var allTermsTextView: TextView
    private lateinit var addButton: Button
    private lateinit var computeButton: Button
    private val allTerms = StringBuilder()

    private val computeActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val sum = data?.getIntExtra("RESULT", 0)
            Toast.makeText(this, "Sum: $sum", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_2main)

        nextTermEditText = findViewById(R.id.next_term)
        allTermsTextView = findViewById(R.id.all_terms)
        addButton = findViewById(R.id.add_button)
        computeButton = findViewById(R.id.compute_button)

        addButton.setOnClickListener {
            val term = nextTermEditText.text.toString()
            if (term.isNotEmpty() && term.toDoubleOrNull() != null) {
                if (allTerms.isNotEmpty()) {
                    allTerms.append(" + ")
                }
                allTerms.append(term)
                allTermsTextView.text = allTerms.toString()
                nextTermEditText.text.clear()
            }
        }

        computeButton.setOnClickListener {
            val intent = Intent(this, Colocviu1_2SecondActivity::class.java).apply {
                putExtra("ALL_TERMS", allTerms.toString())
            }
            computeActivityResultLauncher.launch(intent)
        }
    }
}
