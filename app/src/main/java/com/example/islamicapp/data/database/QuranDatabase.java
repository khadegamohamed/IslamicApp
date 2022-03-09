package com.example.islamicapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.islamicapp.data.pojo.quran.Aya;

@Database(entities = {Aya.class},version =QuranDatabase.DATABASE_VERSION,exportSchema = false )
public abstract  class QuranDatabase extends RoomDatabase {
public static final int DATABASE_VERSION  = 1;
public abstract  QuranDao quranDao();
public static String DATABASE_NAME = "quran.dp";
private static  volatile  QuranDatabase INSTANCE  = null;
public static final QuranDatabase getInstance(final Context context){
    if (INSTANCE == null){
        synchronized (QuranDatabase.class){
            if (INSTANCE == null){
INSTANCE = Room.databaseBuilder(context.getApplicationContext(),QuranDatabase.class,DATABASE_NAME)
        .createFromAsset("quran/databases/quran.db")
        .allowMainThreadQueries()
        .build();
            }
        }
    }
    return INSTANCE;
}

}
