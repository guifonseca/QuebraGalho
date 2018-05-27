package com.app.myapp.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.myapp.R;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CriarServicoFragment extends Fragment {

    private static final int GET_IMG_FILE_1 = 1;
    private static final int GET_IMG_FILE_2 = 2;
    private static final int GET_IMG_FILE_3 = 3;
    private static final int GET_IMG_FILE_4 = 4;
    private static final int GET_IMG_FILE_5 = 5;

    private View view;

    public CriarServicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_criar_servico, container, false);

        configHorizontalScrollView();

        return view;
    }

    private void configHorizontalScrollView() {
        Map<Integer, ImageView> mapImg = new HashMap<Integer, ImageView>();

        mapImg.put(GET_IMG_FILE_1, (ImageView) view.findViewById(R.id.imgServico1));
        mapImg.put(GET_IMG_FILE_2, (ImageView) view.findViewById(R.id.imgServico2));
        mapImg.put(GET_IMG_FILE_3, (ImageView) view.findViewById(R.id.imgServico3));
        mapImg.put(GET_IMG_FILE_4, (ImageView) view.findViewById(R.id.imgServico4));
        mapImg.put(GET_IMG_FILE_5, (ImageView) view.findViewById(R.id.imgServico5));

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
                writeImageFile(data, (ImageView) view.findViewById(R.id.imgServico1), (ImageView) view.findViewById(R.id.imgServico2));
                break;
            case GET_IMG_FILE_2:
                writeImageFile(data, (ImageView) view.findViewById(R.id.imgServico2), (ImageView) view.findViewById(R.id.imgServico3));
                break;
            case GET_IMG_FILE_3:
                writeImageFile(data, (ImageView) view.findViewById(R.id.imgServico3), (ImageView) view.findViewById(R.id.imgServico4));
                break;
            case GET_IMG_FILE_4:
                writeImageFile(data, (ImageView) view.findViewById(R.id.imgServico4), (ImageView) view.findViewById(R.id.imgServico5));
                break;
            case GET_IMG_FILE_5:
                writeImageFile(data, (ImageView) view.findViewById(R.id.imgServico5), null);
                break;
        }
    }

    private void writeImageFile(Intent data, ImageView img, ImageView imgNext) {
        try {
            InputStream is = view.getContext().getContentResolver().openInputStream(data.getData());
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
