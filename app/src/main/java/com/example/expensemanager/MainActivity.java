package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout incomeLayout, expenseLayout, transactionsLayout, reportsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomeLayout = findViewById(R.id.incomeLayout);
        expenseLayout = findViewById(R.id.expenseLayout);
        transactionsLayout = findViewById(R.id.transactionsLayout);
        reportsLayout = findViewById(R.id.reportsLayout);
        incomeLayout.setOnClickListener(this);
        expenseLayout.setOnClickListener(this);
        transactionsLayout.setOnClickListener(this);
        reportsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.incomeLayout: {
                Intent intent = new Intent(this, AddEditIncomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.expenseLayout: {

                Intent intent = new Intent(this, AddEditExpenseActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.transactionsLayout: {
                Intent intent = new Intent(this, ShowTransactionsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.reportsLayout: {
                Intent intent = new Intent(this, ReportsActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}


