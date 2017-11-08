package com.example.patcharaponjoksamut.moneyflow.Utility;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.patcharaponjoksamut.moneyflow.Model.Transaction;

import java.util.List;

/**
 * Created by patcharaponjoksamut on 8/11/2017 AD.
 */

@Dao
public interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    List<Transaction> getAll();

    @Insert
    void insertAll(Transaction... transactions);

    @Delete
    void delete(Transaction transaction);

    @Update
    void update(Transaction transaction);

}
