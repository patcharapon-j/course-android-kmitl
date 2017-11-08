package com.example.patcharaponjoksamut.moneyflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TransactionDialogFragment transactionDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddButtonPressed(View view) {
        transactionDialogFragment = new TransactionDialogFragment();
        transactionDialogFragment.show(getSupportFragmentManager(), "Add new Transaction");
    }

    public void onExpenseButtonPressed(View view) {
        transactionDialogFragment.onExpenseButtonPressed();
    }

    public void onIncomeButtonPressed(View view) {
        transactionDialogFragment.onIncomeButtonPressed();
    }
}
