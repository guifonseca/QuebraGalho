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
import android.widget.ImageView;
import android.widget.Toast;

import com.app.queroumtrampo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.app.queroumtrampo.Constant.EXTRA.IMAGE;
import static com.app.queroumtrampo.Constant.EXTRA.IMAGE_DIRECTORY;

public class CriarTrampoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_trampo);

        try {
            createImagesDirectory();
            loadImages();
        } catch (IOException ioe) {
            Log.e("CriarServicoActivity", ioe.getMessage(), ioe);
        }
    }

    private void createImagesDirectory() throws IOException {
        File dir = getCacheDir();
        boolean bExist = false;

        for (File file : dir.listFiles()) {
            if (file.isDirectory() && file.getName().equals(IMAGE_DIRECTORY)) {
                bExist = true;
                break;
            }
        }

        if (!bExist)
            new File(dir.getAbsolutePath() + "/" + IMAGE_DIRECTORY).mkdirs();
    }

    public void onClickImage(View view) {
        String filename = getIntent().getStringExtra(String.valueOf(view.getId()));

        if (filename != null) {
            Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
            intent.putExtra(IMAGE, filename);

            startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            getIntent().putExtra(IMAGE, view.getId());
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
        switch (requestCode) {
            case 0:
                configImages(data);
                break;

            case 1:
                try {
                    loadImages();
                } catch (IOException e) {
                    Log.e("CriarTrampoActivity", e.getMessage(), e);
                }
                break;
        }
    }

    private void loadImages() throws IOException {
        File dir = new File(getCacheDir().getAbsolutePath() + "/" + IMAGE_DIRECTORY);
        Set<File> files = new LinkedHashSet<File>();
        ImageView[] images = {findViewById(R.id.imgServico1), findViewById(R.id.imgServico2), findViewById(R.id.imgServico3),
                findViewById(R.id.imgServico4), findViewById(R.id.imgServico5)};

        //Reinicia o conjunto de views de imagem para o estado inicial
        for(int i = 0; i < images.length; i++){
            File file = new File(dir.getAbsolutePath() + "/" + String.valueOf(images[i].getId()) + ".jpg");
            getIntent().removeExtra(String.valueOf(images[i].getId()));

            images[i].setVisibility(View.GONE);
            if (i == 0)
                images[i].setVisibility(View.VISIBLE);
            images[i].setImageDrawable(getResources().getDrawable(R.drawable.android_logo));

            if(file.exists())
                files.add(file);
        }

        if (dir.exists()) {
            for (int i = 0; i < images.length; i++) {
                if(files == null || files.isEmpty())
                    continue;

                File file = files.iterator().next();
                files.remove(file);

                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    fis.close();

                    images[i].setImageBitmap(bitmap);

                    if (i < 4)
                        images[i + 1].setVisibility(ImageView.VISIBLE);

                    getIntent().putExtra(String.valueOf(images[i].getId()), file.getName());
                }
            }
        }
    }

    private void configImages(Intent data) {
        int viewId = getIntent().getIntExtra(IMAGE, 0);
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
            String filename = String.valueOf(img.getId()) + ".jpg";
            InputStream is = getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();

            img.setImageBitmap(bitmap);

            FileOutputStream os = new FileOutputStream(new File(getCacheDir().getAbsolutePath() + "/" + IMAGE_DIRECTORY, filename));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();

            getIntent().putExtra(String.valueOf(img.getId()), filename);
        } catch (Exception e) {
            Log.e("CriarServicoActivity", e.getMessage(), e);
        }
    }
}
