package com.example.planetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlanetAdapter extends ArrayAdapter<Planet> {

    ArrayList<Planet> items;
    Context context;

    public PlanetAdapter(ArrayList<Planet> items, Context context) {
        super(context,R.layout.item_list,items);
        this.items = items;
        this.context = context;
    }



    public static class ViewHolder{
        TextView planetName,moonCount;
        ImageView planetImage;




    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Planet planet = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_list,parent,false);


            viewHolder.planetName = convertView.findViewById(R.id.planetName);
            viewHolder.moonCount = convertView.findViewById(R.id.moonCount);
            viewHolder.planetImage = convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);

        }

        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.planetName.setText(planet.getPlanetName());
        viewHolder.moonCount.setText(planet.getMoonCount());
        viewHolder.planetImage.setImageResource(planet.getPlanetImage());

        return convertView;
    }
}
