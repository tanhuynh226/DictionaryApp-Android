package com.example.dictionary;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NewWordActivity extends AppCompatActivity {
    // Variable Declarations
    private EditText word;
    private EditText frequency;
    private EditText meaning;

    // Dictionary Hash Table
    public static LinkedHashMap<String, String> dict = new LinkedHashMap<>();
    // Frequency Array List
    public static ArrayList<Integer> frequencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        // Variable Initializations
        word = findViewById(R.id.new_word_input);
        frequency = findViewById(R.id.frequency_input);
        meaning = findViewById(R.id.body2);
    }

    // Home Page Button
    public void homePageButton(View view){
        Intent intent = new Intent(NewWordActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Clear Function
    private void clear() {
        word.setText("");
        frequency.setText("");
        meaning.setText("");
    }

    // Add Button
    public void addButton(View view){
        if (!dict.containsKey(word)) {
            dict.put(word.getText().toString(), meaning.getText().toString());
            if (frequency.getText().toString().equals(""))
                frequencyList.add(1);
            else
                frequencyList.add(Integer.parseInt(frequency.getText().toString()));
        }

        clear();
    }

    // Clear Button
    public void clearButton(View view){
        clear();
    }

}