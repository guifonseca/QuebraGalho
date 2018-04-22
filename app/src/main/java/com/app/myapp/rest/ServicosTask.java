package com.app.myapp.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.app.myapp.model.Categorias;
import com.app.myapp.model.Servicos;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ServicosTask extends AsyncTask<Void, Void, Servicos> {
    @Override
    protected Servicos doInBackground(Void... voids) {
        Servicos servicos = null;

        try {
            final String url = "http://192.168.25.221:8080/JerseyDemos/rest/servicos";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            servicos = restTemplate.getForObject(url, Servicos.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return servicos;
    }
}
