package com.qf.roombasic_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonInsert;
    Button buttonUpdate;
    Button buttonClear;
    Button buttonDelete;
    TextView textView;
    WordViewModel wordViewModel;
    String TAG = "custom_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        textView = findViewById(R.id.textView1);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                String text = "";
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < words.size(); i++) {
                    Word word = words.get(i);
                    stringBuilder.append(word.getId() + " : " + word.getEnglishWord() + " = " + word.getChineseWord() + "\n");
                }

                textView.setText(stringBuilder.toString());
            }
        });

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("hello", "你好");
                Word word2 = new Word("up", "上");
                wordViewModel.insertWords(word1, word2);
                Log.d(TAG,"INSERT is worked");
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("morning", "早");
                word1.setId(51);
                wordViewModel.updateWords(word1);
                Log.d(TAG,"UPDATE is worked");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
                Log.d(TAG,"CLEAR is worked");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("morning", "早");
                word1.setId(52);
                wordViewModel.deleteWords(word1);
                Log.d(TAG,"DELETE is worked");
            }
        });
    }
}