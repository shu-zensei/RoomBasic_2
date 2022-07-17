package com.qf.roombasic_2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    public void insertWord(com.qf.roombasic_2.Word...words);

    @Update
    public int updateWords(com.qf.roombasic_2.Word...words);

    @Delete
    public int deleteWords(com.qf.roombasic_2.Word...words);

    @Query("DELETE FROM WORD")
    public void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    public LiveData<List<com.qf.roombasic_2.Word>> getAllWordsLive();
}
