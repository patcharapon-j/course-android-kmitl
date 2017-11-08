package com.example.patcharaponjoksamut.moneyflow.Controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patcharaponjoksamut.moneyflow.Model.Transaction;
import com.example.patcharaponjoksamut.moneyflow.R;
import com.example.patcharaponjoksamut.moneyflow.Utility.AppDatabase;
import com.example.patcharaponjoksamut.moneyflow.View.TransactionDialogFragment;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TransactionDialogFragment.TransactionCallbackListener{

    private TransactionDialogFragment transactionDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateListView();
    }

    public void onAddButtonPressed(View view) {
        transactionDialogFragment = new TransactionDialogFragment();
        transactionDialogFragment.setListener(this);
        transactionDialogFragment.show(getSupportFragmentManager(), "Add new Transaction");
    }

    public void onExpenseButtonPressed(View view) {
        transactionDialogFragment.onExpenseButtonPressed();
    }

    public void onIncomeButtonPressed(View view) {
        transactionDialogFragment.onIncomeButtonPressed();
    }

    @Override
    public void onAddNewTransaction(int mode, String name, double amount) {
        //Toast.makeText(this, mode + " " + name + " " + amount, Toast.LENGTH_SHORT).show();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setMode(mode);
        transaction.setName(name);
        AppDatabase.getAppDatabase(this).transactionDao().insertAll(transaction);
        updateListView();
    }

    private String formatNumberString(double number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(true);

        return  numberFormat.format(number);
    }

    private void updateListView() {
        TextView balanceTextView = findViewById(R.id.balanceTextView);

        Double sum = 0.0;
        Double income = 0.0;

        String output = "";
        for(Transaction transaction: AppDatabase.getAppDatabase(this).transactionDao().getAll()) {
            output += transaction.getName() + " " + transaction.getAmount()*transaction.getMode();
            sum += transaction.getAmount()*transaction.getMode();
            if(transaction.getMode() == 1 ) {
                income += transaction.getAmount();
            }
        }

        balanceTextView.setText(formatNumberString(sum));

        if(sum > income * 0.5) {
            balanceTextView.setTextColor(Color.GREEN);
        } else if (sum >= income * 0.25) {
            balanceTextView.setTextColor(Color.YELLOW);
        } else {
            balanceTextView.setTextColor(Color.RED);
        }

    }
}
