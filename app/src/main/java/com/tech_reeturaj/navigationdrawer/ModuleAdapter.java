package com.tech_reeturaj.navigationdrawer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {
    private List<Module> modules;
    private Deeplistner deeplistner;

    public ModuleAdapter(List<Module> modules) {
        this.modules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Module module = modules.get(position);
        holder.img.setImageResource(module.getImage());
        holder.tv.setText(module.getName());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageview1);
            tv = itemView.findViewById(R.id.textView1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            deeplistner.deepClick(view, getAdapterPosition());
        }
    }

    public void setOnDeepClickListner(Deeplistner deepClickListner){
        this.deeplistner = deepClickListner;
    }

    public interface Deeplistner{
        public void deepClick(View view, int position);
    }
}
