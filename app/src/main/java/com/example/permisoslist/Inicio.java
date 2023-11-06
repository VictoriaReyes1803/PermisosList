package com.example.permisoslist;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import android.view.View;

public class Inicio extends AppCompatActivity {
    private TextView Contador;
    private static final int PERMISSIONS_REQUEST_CODE = 1987;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Contador = findViewById(R.id.Cont);

        setupCountdownTimer();
    }

    private void setupCountdownTimer() {
        long duration = 5 * 1000;
        long interval = 1000;

        new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                        secondsRemaining / 60, secondsRemaining % 60);
                Contador.setText(sDuration);
            }

            @Override
            public void onFinish() {
                Contador.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Countdown timer has ended", Toast.LENGTH_LONG).show();

                // Verificar los permisos al finalizar el contador
                if (arePermissionsGranted()) {
                    // Si los permisos están concedidos, inicia MainActivity2
                    startActivity(new Intent(Inicio.this, MainActivity2.class));
                    finish(); // Cierra la actividad actual
                } else {
                    startActivity(new Intent(Inicio.this, MainActivity.class));
                    finish(); // Cierra la actividad actual
                }
            }
        }.start();
    }

    private boolean arePermissionsGranted() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}

