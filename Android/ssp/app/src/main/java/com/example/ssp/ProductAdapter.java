package com.example.ssp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    List<ProductModel> product_list;

    public ProductAdapter(Context context, List<ProductModel> product_list) {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (product_list != null && product_list.size() > 0) {
            ProductModel model = product_list.get(position);
            holder.datt.setText(model.getDate());
            holder.timee.setText(model.getTime());
            holder.sw.setText(model.getStweight());
            holder.pw.setText(String.valueOf((int) model.getPrweight()));
            holder.st.setText(model.getSttime());
            holder.pr.setText(model.getPrtime());
            holder.temp.setText(model.getTem());
            holder.in.setText(String.valueOf((int) model.getInac()));
            holder.mc.setText(String.valueOf(model.getMdcnt()));
            holder.bm.setText(String.valueOf(model.getBdmd()));
            holder.mn.setText(model.getMlnam());
            holder.hc.setText(model.getHetcde());
        }
    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView datt, timee, sw, pw, st,pr, temp, in, mc, bm, mn, hc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            datt = itemView.findViewById(R.id.datt);
            timee = itemView.findViewById(R.id.timee);
            sw = itemView.findViewById(R.id.sw);
            pw = itemView.findViewById(R.id.pw);
            st = itemView.findViewById(R.id.st);
            pr=itemView.findViewById(R.id.pt);
            temp = itemView.findViewById(R.id.temp);
            in = itemView.findViewById(R.id.in);
            mc = itemView.findViewById(R.id.mc);
            bm = itemView.findViewById(R.id.bm);
            mn = itemView.findViewById(R.id.mn);
            hc = itemView.findViewById(R.id.hc);
        }
    }
}
