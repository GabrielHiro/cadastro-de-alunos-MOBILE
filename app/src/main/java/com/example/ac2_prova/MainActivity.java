package com.example.ac2_prova;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCadastro = findViewById(R.id.buttonCadastro);
        Button buttonLista = findViewById(R.id.buttonLista);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a tela de cadastro quando o botão for clicado
                Intent intent = new Intent(MainActivity.this, CadastroAlunoActivity.class);
                startActivity(intent);
            }
        });

        buttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a tela de lista quando o botão for clicado
                Intent intent = new Intent(MainActivity.this, ListaAlunosActivity.class);
                startActivity(intent);
            }
        });
    }
}
