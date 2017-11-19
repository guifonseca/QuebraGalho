package com.app.myapp.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.app.myapp.HomeActivity;
import com.app.myapp.model.Categorias;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guilherme on 11/11/2017.
 */

public class CategoriasTask extends AsyncTask<Void, Void, Categorias> {

    @Override
    protected Categorias doInBackground(Void... params) {
        Categorias categorias = null;

        try {
            final String url = "http://192.168.25.165:8080/JerseyDemos/rest/categorias";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            categorias = restTemplate.getForObject(url, Categorias.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return categorias;
    }
    @Override
    protected void onPostExecute(Categorias dados) {
// Faça alguma coisa com os dados
    }
}
