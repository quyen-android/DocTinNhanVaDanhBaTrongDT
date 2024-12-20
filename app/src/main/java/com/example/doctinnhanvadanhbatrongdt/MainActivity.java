package com.example.doctinnhanvadanhbatrongdt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_ASK_PERMISSION = 1001;
    private static final int REQUEST_SMS_ASK_PERMISSION = 1002;

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions(Manifest.permission.READ_CONTACTS, REQUEST_CONTACTS_ASK_PERMISSION)) {
                    xulyMoManHinhDanhBa();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions(Manifest.permission.READ_SMS, REQUEST_SMS_ASK_PERMISSION)) {
                    xulyMoManHinhTinNhan();
                }
            }
        });
    }

    private boolean checkAndRequestPermissions(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_CONTACTS_ASK_PERMISSION) {
                xulyMoManHinhDanhBa();
            } else if (requestCode == REQUEST_SMS_ASK_PERMISSION) {
                xulyMoManHinhTinNhan();
            }
        } else {
            Toast.makeText(this, "Quyền truy cập bị từ chối", Toast.LENGTH_SHORT).show();
        }
    }

    private void xulyMoManHinhTinNhan() {
        Intent intent = new Intent(MainActivity.this, TinNhanActivity.class);
        startActivity(intent);
    }

    private void xulyMoManHinhDanhBa() {
        Intent intent = new Intent(MainActivity.this, DanhBa.class);
        startActivity(intent);
    }

    private void addControl() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }
}
