package com.app.queroumtrampo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.queroumtrampo.R;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CriarTrampoActivity extends AppCompatActivity {

    private static final int GET_IMG_FILE_1 = 1;
    private static final int GET_IMG_FILE_2 = 2;
    private static final int GET_IMG_FILE_3 = 3;
    private static final int GET_IMG_FILE_4 = 4;
    private static final int GET_IMG_FILE_5 = 5;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_trampo);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Novo Trampo");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configHorizontalScrollView();
    }

    private void configHorizontalScrollView() {
        Map<Integer, ImageView> mapImg = new HashMap<Integer, ImageView>();

        mapImg.put(GET_IMG_FILE_1, (ImageView) findViewById(R.id.imgServico1));
        mapImg.put(GET_IMG_FILE_2, (ImageView) findViewById(R.id.imgServico2));
        mapImg.put(GET_IMG_FILE_3, (ImageView) findViewById(R.id.imgServico3));
        mapImg.put(GET_IMG_FILE_4, (ImageView) findViewById(R.id.imgServico4));
        mapImg.put(GET_IMG_FILE_5, (ImageView) findViewById(R.id.imgServico5));

        for (final Map.Entry<Integer, ImageView> entry : mapImg.entrySet()) {
            entry.getValue().setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    configStartActivityForResult(v, entry.getKey());
                }
            });
        }
    }

    private void configStartActivityForResult(View v, int resultCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), resultCode);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(v.getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_IMG_FILE_1:
                writeImageFile(data, (ImageView) findViewById(R.id.imgServico1), (ImageView) findViewById(R.id.imgServico2));
                break;
            case GET_IMG_FILE_2:
                writeImageFile(data, (ImageView) findViewById(R.id.imgServico2), (ImageView) findViewById(R.id.imgServico3));
                break;
            case GET_IMG_FILE_3:
                writeImageFile(data, (ImageView) findViewById(R.id.imgServico3), (ImageView) findViewById(R.id.imgServico4));
                break;
            case GET_IMG_FILE_4:
                writeImageFile(data, (ImageView) findViewById(R.id.imgServico4), (ImageView) findViewById(R.id.imgServico5));
                break;
            case GET_IMG_FILE_5:
                writeImageFile(data, (ImageView) findViewById(R.id.imgServico5));
                break;
        }
    }

    private void writeImageFile(Intent data, ImageView img) {
        writeImageFile(data, img, null);
    }

    private void writeImageFile(Intent data, ImageView img, ImageView imgNext) {
        try {
            InputStream is = getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();

            img.setImageBitmap(bitmap);

            if (imgNext != null)
                imgNext.setVisibility(ImageView.VISIBLE);
        } catch (Exception e) {
            Log.e("CriarServicoActivity", e.getMessage(), e);
        }
    }
}
