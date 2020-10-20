package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    CardView incomeCard,expenseCard,transactionsCard,reportsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomeCard=findViewById(R.id.incomeCard);
        expenseCard=findViewById(R.id.expenseCard);
        transactionsCard=findViewById(R.id.transactionsCard);
        reportsCard=findViewById(R.id.reportsCard);
        incomeCard.setOnClickListener(this);
        expenseCard.setOnClickListener(this);
        transactionsCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        }

    @Override
    public void onClick(View view)
    {
switch(view.getId())
{
    case R.id.incomeCard:
    {
        Intent intent=new Intent(this, AddEditIncomeActivity.class);
        startActivity(intent);
        break;
    }
    case  R.id.expenseCard:
    {

        Intent intent=new Intent(this, AddEditExpenseActivity.class);
        startActivity(intent);
        break;
    }
    case   R.id.transactionsCard:
    {
       Intent intent=new Intent(this,ShowTransactionsActivity.class);
       startActivity(intent);
       break;
    }
    case  R.id.reportsCard:
    {
        Intent intent=new Intent(this,ReportsActivity.class);
        startActivity(intent);
        break;
    }
}

    }
}


