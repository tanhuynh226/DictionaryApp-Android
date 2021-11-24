package com.example.dictionary;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NewWordActivity extends AppCompatActivity {
    // Variable Declarations
    private EditText word;
    private EditText frequency;
    private EditText meaning;
    private int count;

    // Dictionary Hash Map
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
        count = 0;
        if (!word.getText().toString().equals("") && !meaning.getText().toString().equals("")) {
            if (dict.containsKey(word.getText().toString())){ // Overwrite existing word with new frequency and definition
                for (String key : dict.keySet()) {
                    if (key.equals(word.getText().toString()))
                        frequencyList.remove(count);
                    count++;
                }
                dict.remove(word.getText().toString());
                dict.put(word.getText().toString(), meaning.getText().toString());
            }
            else if (!dict.containsKey(word.getText().toString())) // Add new word
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