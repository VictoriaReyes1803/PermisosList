package com.example.permisoslist;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public CompoundButton checked;
    private TextView permissionsView;
    private TextView descriptionView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        permissionsView = itemView.findViewById(R.id.permission);
        descriptionView = itemView.findViewById(R.id.description);
        checked = itemView.findViewById(R.id.checked);
    }

    public void bind(Item item) {
        permissionsView.setText(item.getPermission());
        descriptionView.setText(item.getDescription());
    }

}
