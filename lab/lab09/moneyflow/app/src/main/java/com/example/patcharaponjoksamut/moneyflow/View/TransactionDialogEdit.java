package com.example.patcharaponjoksamut.moneyflow.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.patcharaponjoksamut.moneyflow.R;

/**
 * Created by patcharaponjoksamut on 9/11/2017 AD.
 */

public class TransactionDialogEdit extends DialogFragment{

    public interface TransactionEditCallbackListener {
        void onEditTransaction(int position, int mode, String name, double amount);
        void onDeleteTransaction(int position);
    }

    private int mode =-1;
    private View view;
    private TransactionEditCallbackListener listener;
    private int position;

    private String existingName;
    private double existingAmount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.transaction, null);

        builder.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText nameEditText = view.findViewById(R.id.nameTextInput);
                        EditText amountEditText = view.findViewById(R.id.amountTextInput);
                        listener.onEditTransaction(position, mode, nameEditText.getText().toString(), Double.parseDouble(amountEditText.getText().toString()));

                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDeleteTransaction(position);
                    }
                });

        populateExistingData();

        return builder.create();
    }

    public void onIncomeButtonPressed() {
        Button incomeButton = view.findViewById(R.id.incomeButton);
        Button expenseButton = view.findViewById(R.id.expenseButton);

        incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonGreen));
        incomeButton.setTextColor(Color.WHITE);
        expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
        expenseButton.setTextColor(Color.BLACK);

        mode = 1;

    }

    public void onExpenseButtonPressed() {
        Button incomeButton = view.findViewById(R.id.incomeButton);
        Button expenseButton = view.findViewById(R.id.expenseButton);

        expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonRed));
        expenseButton.setTextColor(Color.WHITE);
        incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
        incomeButton.setTextColor(Color.BLACK);

        mode = -1;
    }

    public void setListener(TransactionEditCallbackListener listener) {
        this.listener = listener;
    }

    public void setupExistingDataField(int position, int mode, String name, double amount) {
        this.mode = mode;
        this.position = position;
        this.existingAmount = amount;
        this.existingName = name;
    }

    private void populateExistingData() {
        EditText nameEditText = view.findViewById(R.id.nameTextInput);
        EditText amountEditText = view.findViewById(R.id.amountTextInput);
        Button incomeButton = view.findViewById(R.id.incomeButton);
        Button expenseButton = view.findViewById(R.id.expenseButton);

        nameEditText.setText(this.existingName);
        amountEditText.setText(String.valueOf(this.existingAmount));

        if(this.mode == 1) {
            incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonGreen));
            incomeButton.setTextColor(Color.WHITE);
            expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
            expenseButton.setTextColor(Color.BLACK);
        } else {
            expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonRed));
            expenseButton.setTextColor(Color.WHITE);
            incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
            incomeButton.setTextColor(Color.BLACK);
        }

    }
}
