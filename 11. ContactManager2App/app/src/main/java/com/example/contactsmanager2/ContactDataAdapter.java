package com.example.contactsmanager2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactDataAdapter extends RecyclerView.Adapter<ContactDataAdapter.MyViewHolder> {

    ArrayList<Contact> contactArrayList;
    Context context;

    public ContactDataAdapter(ArrayList<Contact> contactArrayList, Context context) {
        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactDataAdapter.MyViewHolder holder, int position) {

        Contact contact = contactArrayList.get(position);
        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());

    }

    @Override
    public int getItemCount() {
        if (contactArrayList!=null){
            return contactArrayList.size();

        }
        else {
            return 0;
        }

    }

    public void setContact(ArrayList<Contact> contactArrayList){
        this.contactArrayList = contactArrayList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView);
            email = itemView.findViewById(R.id.textView2);

        }
    }

}
