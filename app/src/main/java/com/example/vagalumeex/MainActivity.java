package com.example.vagalumeex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.app.SearchManager;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    EditText m;
    Button btn, btn2, btn3, btnrecb;
    public SharedPreferences amz;
    public TextView respostass;
    public String armazem;
    public String lastDateShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m = (EditText) findViewById(R.id.music);
        btn = (Button) findViewById(R.id.pes);
        btn2 = (Button) findViewById(R.id.btsave);
        btn3 = (Button) findViewById(R.id.btrec);
        btnrecb = findViewById(R.id.btbanda);
        respostass = findViewById(R.id.respostas);

        btnrecb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostass.setText(getbanda());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armazena();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
            }
        });

    }




    public void verLetra(View view) {


        String queryString = m.getText().toString();
        NetworkInfo networkInfo = null;

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", queryString);
        getSupportLoaderManager().restartLoader(0, queryBundle, MainActivity.this);


    }

    public void armazena() {

        armazem = m.getText().toString();
        SharedPreferences.Editor preferencesEditor = amz.edit();
        preferencesEditor.putString("data", armazem);
        preferencesEditor.apply();


    }

    public void recuperar() {
        SharedPreferences sharedPreferences = getSharedPreferences("datafile", 0);
        lastDateShared = sharedPreferences.getString("data", null);
        m.setText(lastDateShared);

    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaBanda(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {

            JSONObject jsondata = new JSONObject(data);
            JSONObject jsonresponse = jsondata.getJSONObject("response");
            JSONArray jsondocs = jsonresponse.getJSONArray("docs");
            for (int i = 0; i < jsondocs.length(); i++) {
                respostass.append(jsondocs.getJSONObject(i).getString("band") + "\n");
            }
            savebandas(respostass.getText().toString());

        } catch (Exception e) {


            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {


    }

    public void savebandas(String bandas) throws IOException {
        File file = new File(this.getFilesDir(), "FILENAME");
        FileOutputStream fos = openFileOutput("FILENAME", Context.MODE_PRIVATE);
        fos.write(bandas.getBytes());
        fos.close();
    }

    public String getbanda() {
        try {
            FileInputStream fis = new FileInputStream(this.getFileStreamPath("FILENAME"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            String bandaspegas = String.valueOf(ois.readByte());

            ois.close();
            fis.close();
            return bandaspegas;
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}
