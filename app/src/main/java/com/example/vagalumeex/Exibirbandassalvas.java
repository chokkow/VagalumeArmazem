package com.example.vagalumeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Exibirbandassalvas extends AppCompatActivity {

    TextView txte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibirbandassalvas);

        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
        ArrayList<String>ListaBandas = dbh.getAllContacts();

        txte = findViewById(R.id.tvexibir);

        for (int i = 00;i<ListaBandas.size();i++){
            txte.append(ListaBandas.get(i)+ "\n");
        }
    }
}
