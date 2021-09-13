package com.mylibrary.utilsandroid.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mylibrary.utilsandroid.databinding.ItemMenuBinding;
import com.mylibrary.utilsandroid.pojos.ItemMenuPojo;

import java.util.List;

public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder> {
    private List<ItemMenuPojo> list;
    private ItemCallback callback;

    public ListMenuAdapter(List<ItemMenuPojo> list, ItemCallback callback){
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemMenuPojo item = list.get(position);
        holder.binder.lblName.setText(item.getNameItem());
        holder.binder.cardItem.setOnClickListener( v ->{
            callback.getItem(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMenuBinding binder;

        public ViewHolder(ItemMenuBinding binder) {
            super(binder.getRoot());
            this.binder = binder;
        }
    }

    @FunctionalInterface
    public interface ItemCallback{
        ItemMenuPojo getItem(ItemMenuPojo item);
    }
}
