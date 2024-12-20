package com.example.doctinnhanvadanhbatrongdt;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctinnhanvadanhbatrongdt.model.TinNhan;

import java.util.ArrayList;

public class TinNhanActivity extends AppCompatActivity {

    ListView lvTinNhan;
    ArrayList<TinNhan> dsTinNhan;
    ArrayAdapter<TinNhan> adapterTinNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_nhan);
        addControls();
        showAllSmsFromDevice();
    }

    private void showAllSmsFromDevice() {
        Uri uri = Telephony.Sms.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            dsTinNhan.clear();
            while (cursor.moveToNext()) {
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                TinNhan tinNhan = new TinNhan(address, body);
                dsTinNhan.add(tinNhan);
            }
            cursor.close();
            adapterTinNhan.notifyDataSetChanged();
        }
    }

    private void addControls() {
        lvTinNhan = findViewById(R.id.lvTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new ArrayAdapter<>(TinNhanActivity.this, android.R.layout.simple_list_item_1, dsTinNhan);
        lvTinNhan.setAdapter(adapterTinNhan);
    }
}
