package com.example.expensemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEditExpenseActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String EXTRA_TypeOfTransaction="com.example.expensemanager.EXTRA_TypeOfTransaction";
    public static final String EXTRA_AMOUNT="com.example.expensemanager.EXTRA_AMOUNT";
    public static final String EXTRA_CATEGORY="com.example.expensemanager.EXTRA_CATEGORY";
    public static final String EXTRA_MODE="com.example.expensemanager.EXTRA_MODE";
    public static final String EXTRA_DESCRIPTION="com.example.expensemanager.EXTRA_DESCRIPTION";
    public static final String EXTRA_TransactionDate="com.example.expensemanager.EXTRA_TransactionDate";
    public static final String EXTRA_ID="com.example.expensemanager.EXTRA_ID";

    RadioGroup TransactionsRadioGroup;
    RadioButton IncomeRadioButton, ExpenseRadioButton;
    TextView DateText, TimeText;
    String TypeOfTransaction;
    int Amount;
    String TransactionCategory;
    String TransactionMode;
    String description;
    Spinner CategoryTransactionType, transactionMode;
    EditText amountEditText, descriptionEditText;
    int selectedTransactionValueId;
    private TransactionsViewModel transactionsViewModel;
    Date TransactionDate=null;
    Date TransactionTime=null;
    private boolean UpdateTransaction=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        // FOR BACK BUTTON IN INCOME ACTIVITY
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // SPINNER FOR INCOME,EXPENSE CATEGORY

        CategoryTransactionType = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> firstAdapter = new ArrayAdapter<String>(AddEditExpenseActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.category_List));
        firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategoryTransactionType.setAdapter(firstAdapter);

        //  SPINNER FOR INCOME,EXPENSE MODE

        transactionMode = (Spinner) findViewById(R.id.spinnerMode);
        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(AddEditExpenseActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mode_of_income));
        transactionMode.setAdapter(secondAdapter);
        // Initial Setting OF DATE
        DateText = findViewById(R.id.Date);
        settingDateOnButtonInitially();
        TimeText = findViewById(R.id.Time);
        settingTimeOnButtonInitially();
        TimeText.setOnClickListener(this);
        DateText.setOnClickListener(this);

        // INITIALIZING RADIO_BUTTONS AND RADIO_GROUPS,AMOUNT_EDIT_TEXT,DESCRIPTION_EDIT_TEXT
        TransactionsRadioGroup = findViewById(R.id.radioGroup);
        IncomeRadioButton = findViewById(R.id.IncomeRadioButton);
        ExpenseRadioButton = findViewById(R.id.ExpenseRadioButton);
        amountEditText = findViewById(R.id.amount);
        descriptionEditText = findViewById(R.id.notes);

        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Expenses");
            UpdateTransaction=true;

            TransactionsRadioGroup.check(R.id.ExpenseRadioButton);
            amountEditText.setText(intent.getStringExtra(EXTRA_AMOUNT));
            CategoryTransactionType.setSelection(firstAdapter.getPosition(intent.getStringExtra(EXTRA_CATEGORY)));
            transactionMode.setSelection(secondAdapter.getPosition(intent.getStringExtra(EXTRA_MODE)));
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));

            String date=String.valueOf(intent.getStringExtra(EXTRA_TransactionDate));
            String time=intent.getStringExtra(EXTRA_TransactionDate);
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
            DateFormat DateSdf= new SimpleDateFormat("dd/MM/yyyy");
            DateFormat TimeSdf= new SimpleDateFormat("hh:mm a");
            try {
                TransactionDate=formatter.parse(date);
                String DateInText=DateSdf.format(TransactionDate);
                TransactionTime=formatter.parse(time);
                String TimeInText=TimeSdf.format(TransactionTime);
                DateText.setText(DateInText);
                TimeText.setText(TimeInText);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            setTitle("Add Expenses");
            UpdateTransaction=false;
        }

        transactionsViewModel = new ViewModelProvider(this, ViewModelProvider.
                AndroidViewModelFactory.
                getInstance(this.getApplication())).
                get(TransactionsViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if(UpdateTransaction)
        {
        getMenuInflater().inflate(R.menu.deletealltransactions_menu, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.transactionSaved) {
            if(UpdateTransaction==false)
                saveTransaction();
            else if(UpdateTransaction)
            {
                UpdateTransaction();
            }
            }

if(id==R.id.delete_all_transactions)
{
   AlertDialog alertDialog=new AlertDialog.Builder(this)
           .setTitle("Expense Tracker")
           .setMessage("Are You Sure You Want To delete this Transaction")
           .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Transactions transactions=new Transactions(TypeOfTransaction,Amount,TransactionCategory,TransactionMode,description,TransactionDate);
                   transactions.setId(getIntent().getIntExtra(EXTRA_ID,-1));
transactionsViewModel.delete(transactions);
                   Toast.makeText(AddEditExpenseActivity.this,"Transaction Deleted",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(AddEditExpenseActivity.this, ShowTransactionsActivity.class);
                   startActivity(intent);
                   finish();
               }})
           .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.cancel();
               }
           })
           .show();
}
        if (id == android.R.id.home) {
            if(UpdateTransaction==false){
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if(UpdateTransaction){
                Intent intent=new Intent(this, ShowTransactionsActivity.class);
                startActivity(intent);
                finish();
            }        }
        return super.onOptionsItemSelected(item);
    }
    private void saveTransaction()
    {
        amountEditText = findViewById(R.id.amount);
        if (amountEditText.getText().toString().trim().isEmpty()) {
            Toast amount = Toast.makeText(this, "Enter Amount Please",
                    Toast.LENGTH_SHORT);
            amount.show();
        } else {

            selectedTransactionValueId = TransactionsRadioGroup.getCheckedRadioButtonId();
            if (selectedTransactionValueId == IncomeRadioButton.getId()) {
                TypeOfTransaction = "Income";
            } else if (selectedTransactionValueId == ExpenseRadioButton.getId()) {
                TypeOfTransaction = "Expense";
            }
            Amount = Integer.parseInt(amountEditText.getText().toString());
            TransactionCategory = CategoryTransactionType.getSelectedItem().toString();
            TransactionMode = transactionMode.getSelectedItem().toString();
            description = descriptionEditText.getText().toString();

            String dateText=DateText.getText().toString();
            DateFormat sdf= new SimpleDateFormat("hh:mm a dd/MM/yyyy");
            String timeText=TimeText.getText().toString();
            try {
                TransactionDate= sdf.parse(timeText+" "+dateText);

            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Transactions transactions = new Transactions(TypeOfTransaction, Amount, TransactionCategory, TransactionMode, description,TransactionDate);
            transactionsViewModel.insert(transactions);
            Toast savedNote=Toast.makeText(this," Transaction Saved ",Toast.LENGTH_SHORT);
            savedNote.show();
    }}
    private void UpdateTransaction()
    {
        amountEditText = findViewById(R.id.amount);
        if (amountEditText.getText().toString().trim().isEmpty()) {
            Toast amount = Toast.makeText(this, "Enter Amount Please",
                    Toast.LENGTH_SHORT);
            amount.show();
        }
        else {
            selectedTransactionValueId = TransactionsRadioGroup.getCheckedRadioButtonId();
            if (selectedTransactionValueId == IncomeRadioButton.getId()) {
                TypeOfTransaction = "Income";
            } else if (selectedTransactionValueId == ExpenseRadioButton.getId()) {
                TypeOfTransaction = "Expense";
            }
            Amount = Integer.parseInt(amountEditText.getText().toString());
            TransactionCategory = CategoryTransactionType.getSelectedItem().toString();


            TransactionMode = transactionMode.getSelectedItem().toString();
            description = descriptionEditText.getText().toString();

            String dateText=DateText.getText().toString();
            DateFormat sdf= new SimpleDateFormat("hh:mm a dd/MM/yyyy");
            String timeText=TimeText.getText().toString();

            try {
                TransactionDate= sdf.parse(timeText+" "+dateText);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Transactions transactions=new Transactions(TypeOfTransaction,Amount,TransactionCategory,TransactionMode,description,TransactionDate);
        transactions.setId(getIntent().getIntExtra(EXTRA_ID,-1));
        transactionsViewModel.update(transactions);
        Toast.makeText(this,"Transaction Updated ",Toast.LENGTH_SHORT).show();

    }




























    private void settingDateOnButtonInitially() {
        Calendar c = Calendar.getInstance();
        Date initialDate= c.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String newDate = formatter.format(initialDate);
        DateText.setText(newDate);
    }
    private void settingTimeOnButtonInitially() {
        Calendar c = Calendar.getInstance();
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(date);
        TimeText.setText(time);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Date) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date Picker");
        }
        if (view.getId() == R.id.Time) {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time Picker");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date newDate=c.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDates = formatter.format(newDate);
        DateText.setText(currentDates);
        Log.i("dateSelected ", currentDates);

    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        Date time = c.getTime();
        String timeText=dateFormat.format(time);
        Log.i("TimeSet_After_time",timeText);
        TimeText.setText(timeText);
    }

}