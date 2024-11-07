package ro.pub.cs.systems.eim.colocviu1_2mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class Colocviu1_2MainActivity : ComponentActivity() {

    private lateinit var nextTermEditText: EditText
    private lateinit var allTermsTextView: TextView
    private lateinit var addButton: Button
    private lateinit var computeButton: Button
    private val allTerms = StringBuilder()
    private var previousResult: Int? = null

    private val computeActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            previousResult = data?.getIntExtra("RESULT", 0)
            Toast.makeText(this, "Sum: $previousResult", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_2main)

        nextTermEditText = findViewById(R.id.next_term)
        allTermsTextView = findViewById(R.id.all_terms)
        addButton = findViewById(R.id.add_button)
        computeButton = findViewById(R.id.compute_button)

        if (savedInstanceState != null) {
            allTerms.append(savedInstanceState.getString("ALL_TERMS", ""))
            previousResult = savedInstanceState.getInt("PREVIOUS_RESULT")
            allTermsTextView.text = allTerms.toString()
        }

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
            val currentAllTerms = allTerms.toString()
            if (currentAllTerms == savedInstanceState?.getString("ALL_TERMS")) {
                Toast.makeText(this, "Sum: $previousResult", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, Colocviu1_2SecondActivity::class.java).apply {
                    putExtra("ALL_TERMS", currentAllTerms)
                }
                computeActivityResultLauncher.launch(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("ALL_TERMS", allTerms.toString())
        previousResult?.let { outState.putInt("PREVIOUS_RESULT", it) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        allTerms.append(savedInstanceState.getString("ALL_TERMS", ""))
        previousResult = savedInstanceState.getInt("PREVIOUS_RESULT")
        allTermsTextView.text = allTerms.toString()
    }

    // startServiceIfSumisgreaterthan10
}
