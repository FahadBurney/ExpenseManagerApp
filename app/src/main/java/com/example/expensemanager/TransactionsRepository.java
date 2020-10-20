package com.example.expensemanager;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.ArrayList;
import java.util.List;

public class TransactionsRepository
{
   private TransactionsDao transactionsDao;
   private LiveData<List<Transactions>> allTransactions;
   private LiveData<List<Transactions>> modeTransactions;
   private LiveData<List<Transactions>> typeTransactions;
   private LiveData<List<Transactions>> categoryTransactions;

    public TransactionsRepository(Application application)
    {
       TransactionsDatabase database=TransactionsDatabase.getInstance(application);
       transactionsDao=database.transactionsDao();
       allTransactions=transactionsDao.getAllTransactions();
       modeTransactions=transactionsDao.getModeTransactions();
       typeTransactions=transactionsDao.getTypeTransactions();
       categoryTransactions=transactionsDao.getCategoryTransactions();
    }
    public void insert(Transactions transaction)
    {
        new InsertTransactionsAsyncTask(transactionsDao).execute(transaction);
    }
    public void update(Transactions transaction)
    {
        new UpdateTransactionsAsyncTask(transactionsDao).execute(transaction);
    }
    public void delete(Transactions transaction)
    {
        new DeleteTransactionsAsyncTask(transactionsDao).execute(transaction);
    }
    public void deleteAllTransactions()
    {
        new DeleteAllTransactionsAsyncTask(transactionsDao).execute();
    }
    public LiveData<List<Transactions>> getAllTransactions()
    {
        // Room creates automatically  database operations on background thread
        return allTransactions;
    }
    public LiveData<Integer> getCount()
    {
        return transactionsDao.getCount();
    }
    public LiveData<List<Transactions>> getModeTransactions()
    {
        return modeTransactions;
    }
    public LiveData<List<Transactions>> getTypeTransactions()
    {
        return typeTransactions;
    }
    public LiveData<List<Transactions>> getCategoryTransactions()
    {
        return categoryTransactions;
    }


    private static  class InsertTransactionsAsyncTask extends AsyncTask<Transactions,Void,Void>
    {
        // transactionsDao will make databse operations
     private TransactionsDao transactionsDao;
     private InsertTransactionsAsyncTask(TransactionsDao transactionsDao)
     {
        this.transactionsDao=transactionsDao;
     }
        @Override
        protected Void doInBackground(Transactions... transactions) {
         transactionsDao.insert(transactions[0]);
         return null;
        }
    }
    private static  class UpdateTransactionsAsyncTask extends AsyncTask<Transactions,Void,Void>
    {
        private TransactionsDao transactionsDao;
        private UpdateTransactionsAsyncTask(TransactionsDao transactionsDao)
        {
            this.transactionsDao=transactionsDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.update(transactions[0]);
            return null;
        }
    }
    private static  class DeleteTransactionsAsyncTask extends AsyncTask<Transactions,Void,Void>
    {
        private TransactionsDao transactionsDao;
        private DeleteTransactionsAsyncTask(TransactionsDao transactionsDao)
        {
            this.transactionsDao=transactionsDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.delete(transactions[0]);
            return null;
        }
    }
    private static  class DeleteAllTransactionsAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private TransactionsDao transactionsDao;
        private DeleteAllTransactionsAsyncTask(TransactionsDao transactionsDao)
        {
            this.transactionsDao=transactionsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            transactionsDao.deleteAllTransactions();
            Log.i("deleting database","database populated");
            return null;
        }
    }

}
