package com.example.gasolinaouetanol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText txtGas , txtEta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGas = findViewById(R.id.txtGasolina);
        txtEta = findViewById(R.id.txtEtanol);
    }
    private boolean isEmpity(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }

    private double convertNumber(EditText editText){

        return Double.parseDouble(editText.getText().toString().trim().replace(',','.'));
    }

    private double formatResult(Double calc){
        DecimalFormat formatador = new DecimalFormat("0.00");
        return Double.parseDouble((formatador.format(calc)));
    }

    public void calcGas(View view){
        if(isEmpity(txtEta) || isEmpity((txtGas))){
            Toast.makeText(getApplicationContext(), "Preencha o valor do litro da gasolina e etanol", Toast.LENGTH_LONG).show();
            return;
        }
        txtEta.onEditorAction(EditorInfo.IME_ACTION_DONE);
        txtGas.onEditorAction(EditorInfo.IME_ACTION_DONE);
        
        String gasolina = "@drawable/gasolina";
        String etanol = "@drawable/etanol";
        String messagem = "";
        String resourceFinal = "@drawable/padrao";

        double valGas = convertNumber(txtGas);
        double valEta = formatResult( convertNumber(txtEta));

        double calc = formatResult(valGas * 0.70) ;


        if(valEta <= calc){
            //compensa etanol
            resourceFinal = etanol;
            messagem = "compensa abastecer com etanol!";
        }else{
            //compensa gasolina
            resourceFinal = gasolina;
            messagem = "compensa abastecer com gasolina!";
        }

        int imageResource = getResources().getIdentifier(resourceFinal, null, getPackageName());
        Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        ImageView imagemm = (ImageView)findViewById(R.id.imgResult);

        imagemm.setImageDrawable(res);

        Toast.makeText(getApplicationContext(), messagem, Toast.LENGTH_LONG).show();


        txtEta.setText("");
        txtGas.setText("");


    }
}
