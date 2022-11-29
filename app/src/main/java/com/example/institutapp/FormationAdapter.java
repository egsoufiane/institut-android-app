package com.example.institutapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class FormationAdapter extends RecyclerView.Adapter<FormationAdapter.MyViewHolder> {
    Context context;
    static ArrayList<FormationModel> formationModels;
    /*
    static int selectedItem = 0;
    static OnFormationClick onFormationClick;

    public interface OnFormationClick {
        void onClick(int pos);
    }*/

    public FormationAdapter( Context context, ArrayList<FormationModel> formationModels){
        this.context = context;
        this.formationModels = formationModels;

    }

    @NonNull
    @Override
    public FormationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where you inflate the layout ( giving a look to out rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.formation_holder, parent, false);
        return new FormationAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull FormationAdapter.MyViewHolder holder, int position) {
        //assigning values to to the views we created in the formation holder_layout file
        //based on the position of the recycler view
        holder.txtView.setText(formationModels.get(position).getFormationName());
        holder.imgView.setImageResource(formationModels.get(position).getImage());
/*
        if (position == selectedItem){
            // Make card selected
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.grey1));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.grey1));
            holder.cardView.setStrokeWidth(2);
            holder.txtView.setTextColor(context.getColor(R.color.grey1));
            holder.imgView.setColorFilter(ContextCompat.getColor(context,R.color.grey1), PorterDuff.Mode.SRC_IN);
        }else {
            // Make card inactive
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.gray1));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.gray));
            holder.title.setTextColor(context.getColor(R.color.gray));
            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.gray1), PorterDuff.Mode.SRC_IN);
            holder.cardView.setStrokeWidth(0);
        }*/

    }

    @Override
    public int getItemCount() {
        //the number of items you want displayed
        return formationModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the view from our formation_holder layout file and assigning them to varibles
        //kinda like in the onCreate method
        ImageView imgView;
        TextView txtView;
        MaterialCardView cardView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            imgView = itemView.findViewById(R.id.imageViewF);
            txtView = itemView.findViewById(R.id.textViewF);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Confirm.class);
                    i.putExtra("title",formationModels.get(getAdapterPosition()).getFormationName());
                    i.putExtra("image",formationModels.get(getAdapterPosition()).getImage());
                    v.getContext().startActivity(i);
                }
            });
            /*
             cardView= itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    //reset items, so that color changes when we click on card
                    if (onFormationClick != null)
                        onFormationClick.onClick(getAdapterPosition());
                    }

            });*/


        }
    }

    public void filterList(ArrayList <FormationModel> filteredList){
        formationModels = filteredList;
        notifyDataSetChanged();

    }
}
