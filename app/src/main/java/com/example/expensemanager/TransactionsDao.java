package com.example.expensemanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionsDao
{
    @Insert
    void insert(Transactions transactions);
    @Update
    void update(Transactions transactions);
    @Delete
    void delete(Transactions transactions);
    @Query("DELETE FROM transactions_table")
    void deleteAllTransactions();

    @Query("SELECT * FROM transactions_table Order By transactionDate desc")
    LiveData<List<Transactions>> getAllTransactions();

    @Query("SELECT id,typeOfTransaction,amount FROM transactions_table")
    LiveData<List<Transactions>> getTypeTransactions();

    @Query("SELECT id,modeOfTransaction,amount FROM transactions_table")
    LiveData<List<Transactions>> getModeTransactions();

    @Query("SELECT id,category,amount FROM transactions_table")
    LiveData<List<Transactions>> getCategoryTransactions();

    @Query("SELECT COUNT(*) FROM transactions_table")
 LiveData<Integer> getCount();




}
