package com.app.queroumtrampo.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.app.queroumtrampo.model.Categorias;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Guilherme on 11/11/2017.
 */

public class CategoriasTask extends AsyncTask<Void, Void, Categorias> {

    @Override
    protected Categorias doInBackground(Void... params) {
        Categorias categorias = null;

        try {
            final String url = "http://192.168.25.221:8080/JerseyDemos/rest/categorias";
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
