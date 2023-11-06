package com.example.permisoslist;
import com.example.permisoslist.Item;
import com.example.permisoslist.MainActivity;
import android.Manifest;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Item> items;
    private SwitchListener switchListener;

    public interface SwitchListener {
        void onSwitchChanged();
    }

    public MyAdapter(Context context, List<Item> items, SwitchListener switchListener) {
        this.context = context;
        this.items = items;
        this.switchListener = switchListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.itemview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.bind(currentItem);

        holder.checked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestPermission(currentItem.getPermission());
            }
            switchListener.onSwitchChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 1987);
        }
    }
}