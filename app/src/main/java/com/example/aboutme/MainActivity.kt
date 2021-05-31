package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Create actual data
    private val myName: MyName = MyName("Kai Doe")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // REPLACED :  setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) // Replaces setContextView()

        // Set the value of the myName variable in the layout file to the value of the myName variable that you just declared. You can't access the variable in the XML directly. You need to access it through the binding object.
        binding.myName = myName

        // REPLACED :
        // findViewById<Button>(R.id.done_button).setOnClickListener {
        //     addNickname(it) // <- it refers to the done_button, which is the view passed as the argument
        // }
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

        // REPLACED :
        // findViewById<TextView>(R.id.nickname_text).setOnClickListener {
        //     updateNickname(it)
        // }
        binding.nicknameText.setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View) {
        binding.apply {
            nicknameText.text = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // NOT USED ANYMORE :
        // val editText = findViewById<EditText>(R.id.nickname_edit)
        // val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        // Set the text in the nicknameTextView text view to the text that the user entered in the editText, getting it from the text property
        // REPLACED :
        // nicknameTextView.text = editText.text
        // TO THIS : binding.nicknameText.text = binding.nicknameEdit.text.toString()

        // Hide the nickname EditText view by setting the visibility property of editText to View.GONE
        // REPLACED :
        // editText.visibility = View.GONE
        // TO THIS : binding.nicknameEdit.visibility = View.GONE

        // Hide the DONE button by setting the visibility property to View.GONE
        // REPLACED :
        // view.visibility = View.GONE
        // TO THIS : binding.doneButton.visibility = View.GONE

        //  Make the nickname TextView view visible by setting its visibility property to View.VISIBLE
        // REPLACED :
        // nicknameTextView.visibility = View.VISIBLE
        // TO THIS : binding.nicknameText.visibility = View.VISIBLE

        // Hide the keyboard after the user taps the DONE button
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text
        editText.requestFocus()

        // Show the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}