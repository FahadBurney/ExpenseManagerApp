package com.example.expensemanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TransactionsViewModel extends AndroidViewModel
{
    private TransactionsRepository repository;
   private LiveData<List<Transactions>> allTransactions;
    private LiveData<List<Transactions>> modeTransactions;
    private LiveData<List<Transactions>> typeTransactions;
    private LiveData<List<Transactions>> categoryTransactions;
    public TransactionsViewModel(@NonNull Application application)
    {
        super(application);
        repository = new TransactionsRepository(application);
        allTransactions = repository.getAllTransactions();
        modeTransactions=repository.getModeTransactions();
        typeTransactions=repository.getTypeTransactions();
        categoryTransactions=repository.getCategoryTransactions();
    }
    public void insert(Transactions transactions)
    {
        repository.insert(transactions);
    }
    public void update(Transactions transactions)
    {
        repository.update(transactions);
    }
    public void delete(Transactions transactions)
    {
        repository.delete(transactions);
    }
    public void deleteAllTransactions()
    {
        repository.deleteAllTransactions();
    }

    public LiveData<Integer> getCount() { return repository.getCount(); }
    public LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }
    public LiveData<List<Transactions>> getModeTransactions() {
        return modeTransactions;
    }
    public LiveData<List<Transactions>> getTypeTransactions() {
        return typeTransactions;
    }
    public LiveData<List<Transactions>> getCategoryTransactions() {

        return categoryTransactions;
    }



}
