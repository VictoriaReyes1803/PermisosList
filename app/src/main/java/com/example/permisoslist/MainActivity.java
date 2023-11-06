// MainActivity.java
package com.example.permisoslist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.SwitchListener {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> items = new ArrayList<>();
    private List<String> permissionsToRequest = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        addPermissionAndItem(Manifest.permission.CALL_PHONE, "Cellphone");
        addPermissionAndItem(Manifest.permission.CAMERA, "Camera");
        addPermissionAndItem(Manifest.permission.RECORD_AUDIO, "Microphone");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, items, this);
        recyclerView.setAdapter(adapter);

        if (areAllPermissionsGranted() && areAllSwitchesChecked()) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        } else {
            // Solicita los permisos que faltan
            requestRemainingPermissions();
        }
    }

    @Override
    public void onSwitchChanged() {
        if (areAllPermissionsGranted() && areAllSwitchesChecked()) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    public void addPermissionAndItem(String permission, String description) {
        this.items.add(new Item(permission, description)); // Agrega 'this' para referenciar la variable de instancia
        permissionsToRequest.add(permission);
    }


    private boolean areAllPermissionsGranted() {
        for (String permission : permissionsToRequest) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestRemainingPermissions() {
        for (String permission : permissionsToRequest) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1987);
            }
        }
    }

    private boolean areAllSwitchesChecked() {
        for (Item item : items) {
            if (!item.isChecked()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1987) {
            Log.d("Permisos", "Código de solicitud: 1987");
            for (int i = 0; i < permissions.length; i++) {
                Log.d("Permisos", "Permiso: " + permissions[i] + ", Resultado: " + grantResults[i]);
            }

            if (areAllPermissionsGranted() && areAllSwitchesChecked()) {
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
