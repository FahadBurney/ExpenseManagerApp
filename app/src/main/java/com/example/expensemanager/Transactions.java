package com.example.expensemanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "transactions_table")
public class Transactions {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String typeOfTransaction;

    private int amount;

    private String category;

    private String modeOfTransaction;

    private String description;

    @TypeConverters({Converters.class})
    private Date transactionDate;

    public Transactions(String typeOfTransaction, int amount, String category,
                        String modeOfTransaction, String description, Date transactionDate) {

        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.category = category;
        this.modeOfTransaction = modeOfTransaction;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getModeOfTransaction() {
        return modeOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }
}
