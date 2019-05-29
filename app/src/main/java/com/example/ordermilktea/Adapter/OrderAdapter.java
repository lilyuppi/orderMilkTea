package com.example.ordermilktea.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ordermilktea.Model.Store;
import com.example.ordermilktea.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Store> listStore;

    public OrderAdapter(Context context, ArrayList<Store> listStore) {
        this.context = context;
        this.listStore = listStore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Store store = listStore.get(i);
        int set = store.getNumberOfOrders();
        for (int i1 = 0; i1 < listStore.size(); i1++) {
            if (set > 200) {
                viewHolder.tvName.setText(store.getName());
                Glide.with(context).load(store.getImgSrc()).into(viewHolder.imvAvatar);
                viewHolder.tvNum.setText(String.valueOf(set) + "+");
            }
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onItemClick(store);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listStore.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvAvatar;
        TextView tvName;
        TextView tvNum;
        CardView relativeLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvAvatar = itemView.findViewById(R.id.imagevieworder);
            tvName = itemView.findViewById(R.id.textvieworder);
            tvNum = itemView.findViewById(R.id.textviewset);
            relativeLayout = itemView.findViewById(R.id.relative_layout_order);

        }
    }

    public interface OnItemClickedListener {
        void onItemClick(Store store);
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
