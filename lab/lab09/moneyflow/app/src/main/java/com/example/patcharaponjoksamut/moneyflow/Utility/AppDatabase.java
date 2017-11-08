package com.example.patcharaponjoksamut.moneyflow.Utility;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.patcharaponjoksamut.moneyflow.Model.Transaction;

/**
 * Created by patcharaponjoksamut on 8/11/2017 AD.
 */

@Database(entities = {Transaction.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTNACE;

    public abstract TransactionDao transactionDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTNACE == null) {
            INSTNACE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "transaction-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTNACE;
    }

    public static void destroyInstance() {
        INSTNACE = null;
    }

}
