package com.example.permisoslist;

import android.widget.Switch;

public class Item {
    private String permission;
    private String description;
    private boolean checked;

    public Item(String permission, String description) {
        this.permission = permission;
        this.description = description;
        this.checked = false;

    }

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}
