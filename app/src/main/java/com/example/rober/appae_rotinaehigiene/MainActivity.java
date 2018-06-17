package com.example.rober.appae_rotinaehigiene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declaração de variaveis
    private Button b1;
    private Button b2;
    private Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atribuindo valores as variaveis
        b1 = (Button) findViewById(R.id.fotos);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.imagens);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == b2) {
            Intent intent = new Intent(this, imagens.class);
            startActivity(intent);//chamando a tela de imagens
        }
        else if(v == b1) {
            Intent intent = new Intent(this, fotos.class);
            startActivity(intent);//chamando a tela de fotos
        }

    }
}
