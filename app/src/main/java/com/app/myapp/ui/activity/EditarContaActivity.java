package com.app.myapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.myapp.R;
import com.app.myapp.util.MaskEditTextChangedListener;

/**
 * Created by Guilherme on 19/11/2017.
 */

public class EditarContaActivity extends AppCompatActivity {
    private EditText cpfCnpjEditText;
    private RadioGroup cpfCnpjRadioButton;
    private String mask = "###.###.###-##";

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState  );
        setContentView( R.layout.activity_editar_conta);

        cpfCnpjEditText = (EditText)findViewById(R.id.txCPFCNPJ);

        cpfCnpjEditText.addTextChangedListener(new MaskEditTextChangedListener(mask,cpfCnpjEditText ));
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        cpfCnpjEditText = (EditText)findViewById(R.id.txCPFCNPJ);

        cpfCnpjEditText.setText("");

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbCPF:
                if (checked)
                    mask = "###.###.###-##";
                    break;
            case R.id.rbCNPJ:
                if (checked)
                    mask = "##.###.###/####-##";
                    break;
        }

        cpfCnpjEditText.addTextChangedListener(new MaskEditTextChangedListener(mask,cpfCnpjEditText ));
    }
}
