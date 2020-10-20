package com.example.expensemanager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

@Database(entities = {Transactions.class},version=1)
@TypeConverters({Converters.class})
public abstract class TransactionsDatabase extends RoomDatabase
{
    private static TransactionsDatabase instance;

    public abstract TransactionsDao transactionsDao();
    // room subclasses our abstract class
    public static synchronized TransactionsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionsDatabase.class,
                    "Transactions_database.db").fallbackToDestructiveMigration().
                    addCallback(roomCallBack).
                    build();
        }

        return instance;
}
private  static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback() {
        @Override
    // this method is called after database is created

    public void onCreate(@NonNull SupportSQLiteDatabase db) {

        super.onCreate(db);

    //    new PopulateDbAsyncTask(instance).execute();

    }
};
    private  static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private TransactionsDao transactionsDao;
        private PopulateDbAsyncTask(TransactionsDatabase db)
        {
        transactionsDao=db.transactionsDao();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            return null;
        }
    }
}
