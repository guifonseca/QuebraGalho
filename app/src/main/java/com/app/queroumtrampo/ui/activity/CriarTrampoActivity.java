package com.app.queroumtrampo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.queroumtrampo.Constant;
import com.app.queroumtrampo.R;
import com.google.android.gms.common.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CriarTrampoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_trampo);
    }

    public void onClickImage(View view) {
        String filename = getIntent().getStringExtra(String.valueOf(view.getId()));

        if (filename != null) {
            Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
            intent.putExtra(Constant.EXTRA.IMAGE, filename);

            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            getIntent().putExtra(Constant.EXTRA.IMAGE, view.getId());
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            try {
                startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 0);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(view.getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int viewId = getIntent().getIntExtra(Constant.EXTRA.IMAGE, 0);
        writeImageFile(data, (ImageView) findViewById(viewId));

        switch (viewId) {
            case R.id.imgServico1:
                findViewById(R.id.imgServico2).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico2:
                findViewById(R.id.imgServico3).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico3:
                findViewById(R.id.imgServico4).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico4:
                findViewById(R.id.imgServico5).setVisibility(View.VISIBLE);
                break;
        }
    }

    private void writeImageFile(Intent data, ImageView img) {
        try {
            String filename = "imagem_" + String.valueOf(img.getId()) + ".jpg";
            InputStream is = getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();

            img.setImageBitmap(bitmap);

            FileOutputStream os = new FileOutputStream(new File(getCacheDir(), filename));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();

            getIntent().putExtra(String.valueOf(img.getId()), filename);
        } catch (Exception e) {
            Log.e("CriarServicoActivity", e.getMessage(), e);
        }
    }
}
