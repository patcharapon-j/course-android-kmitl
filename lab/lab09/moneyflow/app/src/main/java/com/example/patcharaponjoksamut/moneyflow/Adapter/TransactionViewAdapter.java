package com.example.patcharaponjoksamut.moneyflow.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patcharaponjoksamut.moneyflow.Model.Transaction;
import com.example.patcharaponjoksamut.moneyflow.R;

import java.util.List;

/**
 * Created by patcharaponjoksamut on 8/11/2017 AD.
 */

public class TransactionViewAdapter extends RecyclerView.Adapter<TransactionViewAdapter.Holder> {

    private List<Transaction> allTransaction;

    public void setAllTransaction(List<Transaction> allTransaction) {
        this.allTransaction = allTransaction;
    }

    public TransactionViewAdapter() {

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Transaction transaction = allTransaction.get(position);
        holder.amountTextView.setText(String.valueOf(transaction.getAmount()));
        holder.nameTextView.setText(transaction.getName());
        holder.nameTextView.setTextColor(Color.WHITE);

        if(transaction.getMode() == 1) {
            holder.modeTextView.setText("+");
            holder.amountTextView.setTextColor(Color.GREEN);
            holder.modeTextView.setTextColor(Color.GREEN);
        } else {
            holder.modeTextView.setText("-");
            holder.amountTextView.setTextColor(Color.RED);
            holder.modeTextView.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return allTransaction.size();
    }

    class Holder extends RecyclerView.ViewHolder  {

        public TextView modeTextView;
        TextView nameTextView;
        TextView amountTextView;

        public Holder(View itemView) {
            super(itemView);

            modeTextView = itemView.findViewById(R.id.itemmode);
            nameTextView = itemView.findViewById(R.id.itemname);
            amountTextView = itemView.findViewById(R.id.itemamount);

        }
    }
}
