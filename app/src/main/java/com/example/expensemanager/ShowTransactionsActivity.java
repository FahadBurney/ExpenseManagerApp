package com.example.expensemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowTransactionsActivity extends AppCompatActivity {

    private TransactionsViewModel transactionsviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transactions);

        final RecyclerView recyclerView=findViewById(R.id.recycler_view);

        final TransactionsAdapter adapter=new TransactionsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(ShowTransactionsActivity.this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        transactionsviewmodel = new ViewModelProvider(ShowTransactionsActivity.this,
                ViewModelProvider.
                        AndroidViewModelFactory.
                        getInstance(this.getApplication())).
                get(TransactionsViewModel.class);


        transactionsviewmodel.getAllTransactions().observe(this,
                new Observer<List<Transactions>>() {
                    @Override
                    public void onChanged(List<Transactions> transactions) {
                        adapter.setTransactions(transactions);
                    }
                });
adapter.setOnItemClickListener(new TransactionsAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(Transactions transactions,int position) {
        TextView typeOfTransaction=(TextView)recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.text_view_typeOfTransaction);
        String transactionType=typeOfTransaction.getText().toString();
       if(transactionType.equals("Income"))
       {
           Intent intent=new Intent(ShowTransactionsActivity.this, AddEditIncomeActivity.class);
           intent.putExtra(AddEditIncomeActivity.EXTRA_TypeOfTransaction,transactions.getTypeOfTransaction());
           intent.putExtra(AddEditIncomeActivity.EXTRA_AMOUNT,String.valueOf(transactions.getAmount()));
           intent.putExtra(AddEditIncomeActivity.EXTRA_CATEGORY,transactions.getCategory());
           intent.putExtra(AddEditIncomeActivity.EXTRA_MODE,transactions.getModeOfTransaction());
           intent.putExtra(AddEditIncomeActivity.EXTRA_TransactionDate,String.valueOf(transactions.getTransactionDate()));
           intent.putExtra(AddEditIncomeActivity.EXTRA_DESCRIPTION,transactions.getDescription());
           intent.putExtra(AddEditIncomeActivity.EXTRA_ID,transactions.getId());
           startActivity(intent);
           finish();
       }
       else if(transactionType.equals("Expense"))
       {
          Intent intent=new Intent(ShowTransactionsActivity.this, AddEditExpenseActivity.class);
          intent.putExtra(AddEditExpenseActivity.EXTRA_TypeOfTransaction,transactions.getTypeOfTransaction());
          intent.putExtra(AddEditExpenseActivity.EXTRA_AMOUNT,String.valueOf(transactions.getAmount()));
           intent.putExtra(AddEditExpenseActivity.EXTRA_CATEGORY,transactions.getCategory());
           intent.putExtra(AddEditExpenseActivity.EXTRA_MODE,transactions.getModeOfTransaction());
           intent.putExtra(AddEditExpenseActivity.EXTRA_TransactionDate,String.valueOf(transactions.getTransactionDate()));
           intent.putExtra(AddEditExpenseActivity.EXTRA_DESCRIPTION,transactions.getDescription());
           intent.putExtra(AddEditExpenseActivity.EXTRA_ID,transactions.getId());
           startActivity(intent);
           finish();
       }


    }
});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
    return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.delete_all_transactions:
                transactionsviewmodel.deleteAllTransactions();
                Toast.makeText(this,"All Transactions Deleted",Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }
}