package com.app.queroumtrampo.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.queroumtrampo.QueroUmTrampoApplication;
import com.app.queroumtrampo.R;
import com.app.queroumtrampo.ui.activity.FullscreenActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.app.queroumtrampo.Constant.EXTRA.IMAGE;
import static com.app.queroumtrampo.Constant.EXTRA.IMAGE_DIRECTORY;
import static com.app.queroumtrampo.Constant.EXTRA.IMAGE_VIEW_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriarTrampoFragment extends BaseFragment {

    private Context context;
    private View view;
    private HorizontalScrollView horizontalScrollView;

    public CriarTrampoFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivityCallback.setScreenTitle("Criar Trampo");

        this.view = view;

        ImageView imageView = view.findViewById(R.id.imgServico1);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(v);
            }
        });

        imageView = view.findViewById(R.id.imgServico2);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(v);
            }
        });

        imageView = view.findViewById(R.id.imgServico3);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(v);
            }
        });

        imageView = view.findViewById(R.id.imgServico4);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(v);
            }
        });

        imageView = view.findViewById(R.id.imgServico5);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(v);
            }
        });

        this.horizontalScrollView = view.findViewById(R.id.horizontalScrollView);

        try {
            createImagesDirectory();
            loadImages();
        } catch (IOException ioe) {
            Log.e("CriarTrampoFragment", ioe.getMessage(), ioe);
        }
    }

    private void createImagesDirectory() throws IOException {
        File dir = context.getCacheDir();
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
        String filename = (String) QueroUmTrampoApplication.getGlobalObject().get(String.valueOf(view.getId()));

        if (filename != null) {
            Intent intent = new Intent(context.getApplicationContext(), FullscreenActivity.class);
            QueroUmTrampoApplication.getGlobalObject().put(IMAGE, filename);

            startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            QueroUmTrampoApplication.getGlobalObject().put(IMAGE_VIEW_ID, view.getId());
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
                try {
                    configImages(data);
                } catch (IOException e){
                    Log.e("CriarTrampoActivity", e.getMessage(), e);
                }
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
        File dir = new File(context.getCacheDir().getAbsolutePath() + "/" + IMAGE_DIRECTORY);
        Set<File> files = new LinkedHashSet<File>();
        ImageView[] images = {view.findViewById(R.id.imgServico1), view.findViewById(R.id.imgServico2), view.findViewById(R.id.imgServico3),
                view.findViewById(R.id.imgServico4), view.findViewById(R.id.imgServico5)};

        //Reinicia o conjunto de views de imagem para o estado inicial
        for (int i = 0; i < images.length; i++) {
            File file = new File(dir.getAbsolutePath() + "/" + String.valueOf(images[i].getId()) + ".jpg");
            QueroUmTrampoApplication.getGlobalObject().remove(String.valueOf(images[i].getId()));

            images[i].setVisibility(View.GONE);
            if (i == 0)
                images[i].setVisibility(View.VISIBLE);
            images[i].setImageDrawable(getResources().getDrawable(R.drawable.android_logo));

            if (file.exists())
                files.add(file);
        }

        if (dir.exists()) {
            for (int i = 0; i < images.length; i++) {
                if (files == null || files.isEmpty())
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

                    QueroUmTrampoApplication.getGlobalObject().put(String.valueOf(images[i].getId()), file.getName());
                }
            }
        }

        horizontalScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 10);
    }

    private void configImages(Intent data) throws IOException {
        int viewId = (Integer)QueroUmTrampoApplication.getGlobalObject().get(IMAGE_VIEW_ID);
        writeImageFile(data, (ImageView) view.findViewById(viewId));

        switch (viewId) {
            case R.id.imgServico1:
                view.findViewById(R.id.imgServico2).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico2:
                view.findViewById(R.id.imgServico3).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico3:
                view.findViewById(R.id.imgServico4).setVisibility(View.VISIBLE);
                break;
            case R.id.imgServico4:
                view.findViewById(R.id.imgServico5).setVisibility(View.VISIBLE);
                break;
        }

        QueroUmTrampoApplication.getGlobalObject().remove(IMAGE_VIEW_ID);

        horizontalScrollView.postDelayed(new Runnable() {

            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 10);
    }

    private void writeImageFile(Intent data, ImageView img) throws IOException {
        String filename = String.valueOf(img.getId()) + ".jpg";
        InputStream is = context.getContentResolver().openInputStream(data.getData());
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        is.close();

        img.setImageBitmap(bitmap);

        FileOutputStream os = new FileOutputStream(new File(context.getCacheDir().getAbsolutePath() + "/" + IMAGE_DIRECTORY, filename));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        os.close();

        QueroUmTrampoApplication.getGlobalObject().put(String.valueOf(img.getId()), filename);
    }

    @Override
    protected int getViewID() {
        return R.layout.fragment_criar_trampo;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
