package com.example.patcharaponjoksamut.moneyflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddButtonPressed(View view) {
        TransactionDialogFragment transactionDialogFragment = new TransactionDialogFragment();
        transactionDialogFragment.show(getSupportFragmentManager(), "Add new Transaction");
    }
}
