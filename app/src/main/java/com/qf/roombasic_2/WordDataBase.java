package com.qf.roombasic_2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * entities 里面可以用逗号分割多个class
 * version 每次entity类发生改变的时候，version也必须修改+1
 */
// SINGLETON, DATABASE实例化是非常耗资源的，会生成多个实例，在程序中多个地方调用这个数据库实例防止产生多个实例，每次只返回同一个实例
@Database(entities = {com.qf.roombasic_2.Word.class}, version = 1, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    private static WordDataBase INSTANCE;

    // synchronized 保证多个客户端访问的话，防止并发
    public synchronized static WordDataBase getDatabase(Context context){
        if (INSTANCE == null) {
            // getApplicationContext() 生命周期是整个应用，应用摧毁它才摧毁
            // 整个应用程序的Context
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database").build();
        }
        return INSTANCE;
    }

    public abstract com.qf.roombasic_2.WordDao getWordDao();

}
