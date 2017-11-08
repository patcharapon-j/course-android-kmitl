package com.example.patcharaponjoksamut.moneyflow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by patcharaponjoksamut on 8/11/2017 AD.
 */

public class TransactionDialogFragment extends DialogFragment {

    private int mode = 1;
    private View view;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.transaction, null);

        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public void onIncomeButtonPressed() {
        Button incomeButton = view.findViewById(R.id.incomeButton);
        Button expenseButton = view.findViewById(R.id.expenseButton);

        incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonGreen));
        incomeButton.setTextColor(Color.WHITE);
        expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
        expenseButton.setTextColor(Color.BLACK);

        mode = 0;

    }

    public void onExpenseButtonPressed() {
        Button incomeButton = view.findViewById(R.id.incomeButton);
        Button expenseButton = view.findViewById(R.id.expenseButton);

        expenseButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonRed));
        expenseButton.setTextColor(Color.WHITE);
        incomeButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.ButtonWhite));
        incomeButton.setTextColor(Color.BLACK);

        mode = 1;
    }
}
