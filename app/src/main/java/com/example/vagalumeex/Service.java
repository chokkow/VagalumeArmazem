package com.example.vagalumeex;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Service  {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();



    private static final String QUERY_PARAM = "q";

    private static final String MAX_RESULTS = "maxResults";

    static String BuscaBandas(String queryString) {

        String Vag_url = "Https://api.vagalume.com.br/search.excerpt?apikey=660a4395f992ff67786584e238f501aa&q="+ queryString.replace(" ","%" + "")+"&limit=1";
        Log.d("LOG_TAG", Vag_url);
        HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String bandJSONstring = null;
            try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(Vag_url).buildUpon() .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            bandJSONstring = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d("LOG_TAG", bandJSONstring);
        return bandJSONstring;
    }
}






