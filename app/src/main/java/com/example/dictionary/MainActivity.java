package com.example.dictionary;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    // Variable Declarations
    private EditText input;
    private TextView frq_one;
    private TextView frq_two;
    private TextView frq_three;
    private TextView body;
    private String word;

    // Variables used for autocomplete method
    private ArrayList<Integer> frequencies = new ArrayList<>();
    private ArrayList<String> keySet = new ArrayList<>();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variable Initializations
        input = findViewById(R.id.input);
        frq_one = findViewById(R.id.frequent_field_one);
        frq_two = findViewById(R.id.frequent_field_two);
        frq_three = findViewById(R.id.frequent_field_three);
        body = findViewById(R.id.body);
    }

    // New Word Button
    public void newWordButton(View view){
        Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
        startActivity(intent);
    }

    // Find Button
    public void findButton(View view){
        word = input.getText().toString();

        if (NewWordActivity.dict.containsKey(word))
            body.setText(NewWordActivity.dict.get(word));

        if (!word.equals(""))
            autoComplete(word);

        System.out.println(NewWordActivity.dict.keySet());
        System.out.println(NewWordActivity.frequencyList);
    }

    // Remove Button
    public void removeButton(View view){
        count = 0;
        if (NewWordActivity.dict.containsKey(word) && !word.equals("")) {
            for (String key : NewWordActivity.dict.keySet()) {
                if (key.equals(word))
                    NewWordActivity.frequencyList.remove(count);
                count++;
            }
            NewWordActivity.dict.remove(word);
        }
        input.setText("");
        body.setText("");
        frq_one.setText("");
        frq_two.setText("");
        frq_three.setText("");
    }

    // Autocomplete Function
    private void autoComplete(String word){
        count = 0;

        // Clear TextView and ArrayLists
        frequencies.clear();
        keySet.clear();
        frq_one.setText("");
        frq_two.setText("");
        frq_three.setText("");

        // Store frequencies and keys of words that starts with the input
        for (String key : NewWordActivity.dict.keySet()){
            if (key.startsWith(word)) {
                frequencies.add(NewWordActivity.frequencyList.get(count));
                keySet.add(key);
            }
            count++;
        }

        // Sorts keySet based on how frequencies is sorted
        final List<String> stringListCopy = new ArrayList<>(keySet);
        ArrayList<String> sortedList = new ArrayList(stringListCopy);
        Collections.sort(sortedList, (left, right) -> frequencies.get(stringListCopy.indexOf(left)) - frequencies.get(stringListCopy.indexOf(right))); // I would've used Comparator.compareInt but that would require minimum API level of 24

        // Sets the frequent words TextViews
        if (sortedList.size() > 0)
            frq_one.setText(sortedList.get(sortedList.size() - 1));
        if (sortedList.size() > 1)
            frq_two.setText(sortedList.get(sortedList.size() - 2));
        if (sortedList.size() > 2)
            frq_three.setText(sortedList.get(sortedList.size() - 3));
    }
}