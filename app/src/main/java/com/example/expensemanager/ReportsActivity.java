package com.example.expensemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    PieChart anyChartView;
    Spinner CategoryReportType;
    List<Transactions> TransactionsList = new ArrayList<Transactions>();
    int size = -1,l=0;
    int operations=0;
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    private TransactionsViewModel transactionsviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);







        // SPINNER FOR TYPE OF REPORT:CATEGORY,MODE,TYPE
        CategoryReportType = findViewById(R.id.spinnerReport);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.report_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategoryReportType.setAdapter(adapter);
        CategoryReportType.setOnItemSelectedListener(this);

        anyChartView = (PieChart)findViewById(R.id.chart_view);

        transactionsviewmodel = new ViewModelProvider(ReportsActivity.this,
                ViewModelProvider.
                        AndroidViewModelFactory.
                        getInstance(this.getApplication())).
                get(TransactionsViewModel.class);

        transactionsviewmodel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                size = integer;
            }
        });
        if (size != -1 || size != 0) {
            transactionsviewmodel.getTypeTransactions().observe(this,
                    new Observer<List<Transactions>>() {
                @Override
                public void onChanged(List<Transactions> transactions) {
                    map = new HashMap<>();
                    for (int i = 0; i < size; i++) {
                        String type = transactions.get(i).getTypeOfTransaction();
                        int amount = transactions.get(i).getAmount();

                        if (map.containsKey(type)) {
                            int a = map.get(type);
                            map.put(type, a + amount);
                            //              Log.i("Key present",type+" "+(a+amount));

                        } else if (!map.containsKey(type)) {
                            map.put(type, amount);
                        }
                    }
                }
            });
            Log.i("Timing ",(l++)+"");
            if(!map.isEmpty())
            {setUpPieChart(map,"TYPE");}
        }
    }

    private void setUpPieChart(HashMap<String, Integer> map,String Reports) {
Log.i("Map_Inside",map+" ");
        List<PieEntry> pieEntries = new ArrayList<>();
        int size = map.size();
        String[] keys = new String[size];
        int[] values = new int[size];
        int x = 0, y = 0;
        for (Map.Entry<String, Integer> me : map.entrySet()) {

            keys[x] = me.getKey();
            x++;
            values[y] = me.getValue();
            y++;
        }
        for (int i = 0; i < size; i++) {
            pieEntries.add(new PieEntry(values[i], keys[i]));
        }
        PieDataSet dataSet=new PieDataSet(pieEntries,Reports);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);
        anyChartView.setData(data);
        anyChartView.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String text = parent.getItemAtPosition(position).toString();
        if (size != -1 || size != 0)
        {

            if (text.equals("Category"))
            {

                transactionsviewmodel.getCategoryTransactions().
                        observe(this, new Observer<List<Transactions>>() {
                    @Override
                    public void onChanged(List<Transactions> transactions) {
                        map = new HashMap<>();
                        for (int i = 0; i < size; i++) {
                            String category = transactions.get(i).getCategory();
                            int amount = transactions.get(i).getAmount();
                            if (map.containsKey(category)) {
                                int a = map.get(category);
                                map.put(category, (a + amount));
                            } else if (!map.containsKey(category)) {
                                map.put(category, amount);
                            }
                        }
                        setUpPieChart(map,"CATEGORY");
                    }
                });

            }
            if (text.equals("Mode"))
            {

                transactionsviewmodel.getModeTransactions().
                        observe(this, new Observer<List<Transactions>>() {
                    @Override
                    public void onChanged(List<Transactions> transactions) {
                        map = new HashMap<String, Integer>();

                        for (int i = 0; i < size; i++) {
                            String mode = transactions.get(i).getModeOfTransaction();
                            int amount = transactions.get(i).getAmount();
                            if (map.containsKey(mode)) {
                                int a = map.get(mode);
                                map.put(mode, a + amount);
                            } else if (!map.containsKey(mode)) {
                                map.put(mode, amount);
                            }

                        }
                        setUpPieChart(map,"MODE");

                    }
                });


            }
            if (text.equals("Type"))
            {

                transactionsviewmodel.getTypeTransactions().
                        observe(this, new Observer<List<Transactions>>() {
                    @Override
                    public void onChanged(List<Transactions> transactions) {
                        map = new HashMap<>();
                        for (int i = 0; i < size; i++) {
                            String type = transactions.get(i).getTypeOfTransaction();
                            int amount = transactions.get(i).getAmount();
                            if (map.containsKey(type)) {
                                int a = map.get(type);
                                map.put(type, a + amount);

                            } else if (!map.containsKey(type)) {
                                map.put(type, amount);
                            }
                        }
                        setUpPieChart(map,"TYPE");

                    }
                });

                }

        } else {
            Log.i("size=-1", size + "");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}