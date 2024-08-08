package com.example.splitifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    ArrayList<Expense> items;
    Context context;

    public ExpenseAdapter(ArrayList<Expense> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense Expense = items.get(position);

        holder.expense_name.setText(Expense.getExpenseName());
        holder.expense_amount.setText(Integer.toString(Expense.getExpenseAmount()));
        holder.expense_date.setText(Expense.getExpenseDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, Expense.getExpenseName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView expense_name,expense_amount,expense_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            expense_name = itemView.findViewById(R.id.expense_name);
            expense_amount = itemView.findViewById(R.id.expense_amount);
            expense_date = itemView.findViewById(R.id.expense_date);

        }
    }
}
