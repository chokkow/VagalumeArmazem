package com.example.vagalumeex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity {


    ImageView fund;
    Button bt1, bt2;
    TextView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        fund = (ImageView) findViewById(R.id.bgfundo);
        bt1 = (Button) findViewById(R.id.btntelaprincipal);
        bt2 = (Button) findViewById(R.id.btnloc);
        r = (TextView) findViewById(R.id.textView3);

    }

    public void vagalume(View view) {
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent it = new Intent(TelaInicial.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    public void Loc (View view) {
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent it = new Intent(TelaInicial.this, Local.class);
                startActivity(it);
            }
        });


    }



}
