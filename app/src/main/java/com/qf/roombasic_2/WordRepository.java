package com.qf.roombasic_2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<com.qf.roombasic_2.Word>> allWordsLive;
    private WordDao wordDao;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getDatabase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    public LiveData<List<com.qf.roombasic_2.Word>> getAllWordsLive() {
        return allWordsLive;
    }

    public void setAllWordsLive(LiveData<List<com.qf.roombasic_2.Word>> allWordsLive) {
        this.allWordsLive = allWordsLive;
    }

    public void updateWords(com.qf.roombasic_2.Word...words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    public void deleteWords(com.qf.roombasic_2.Word...words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    public void insertWords(com.qf.roombasic_2.Word...words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    public void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    /**
     * 防止在主线程执行
     */
    public static class InsertAsyncTask extends AsyncTask<com.qf.roombasic_2.Word, Void, Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(com.qf.roombasic_2.Word... words) {
            wordDao.insertWord(words);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<com.qf.roombasic_2.Word, Void, Void> {
        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(com.qf.roombasic_2.Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<com.qf.roombasic_2.Word, Void, Void> {
        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(com.qf.roombasic_2.Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    public static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
