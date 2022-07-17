package com.qf.roombasic_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private com.qf.roombasic_2.WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new com.qf.roombasic_2.WordRepository(application);
    }

    public LiveData<List<com.qf.roombasic_2.Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    public void updateWords(com.qf.roombasic_2.Word...words){
        wordRepository.updateWords(words);
    }

    public void deleteWords(com.qf.roombasic_2.Word...words){
        wordRepository.deleteWords(words);
    }

    public void insertWords(com.qf.roombasic_2.Word...words){
        wordRepository.insertWords(words);
    }
    public void deleteAllWords(){
        wordRepository.deleteAllWords();
    }
}
