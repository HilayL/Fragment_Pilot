package com.example.smellslikebaking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class ListAdapter extends RecyclerView.Adapter {

    private final ListFragmet.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragmet.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);


        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).BindView(position);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView  mTextView;
        private ImageView mImageView;
        private int mIndex;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.itemText);
            mImageView = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void BindView(int position){
            mIndex = position;
            mTextView.setText(Recipes.names[position]);
            mImageView.setImageResource(Recipes.resourceIds[position]);

        }

        @Override
        public void onClick(View view) {
            mListener.onListRecipeSelected(mIndex);
        }
    }
}
