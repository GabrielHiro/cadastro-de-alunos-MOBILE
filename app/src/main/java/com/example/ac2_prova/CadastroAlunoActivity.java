package com.example.ac2_prova;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText editTextRA, editTextNome, editTextCEP, editTextLogradouro, editTextComplemento, editTextBairro, editTextCidade, editTextUF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        // Obter referências para os elementos da interface do usuário
        editTextRA = findViewById(R.id.editTextRA);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCEP = findViewById(R.id.editTextCEP);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUF = findViewById(R.id.editTextUF);
        Button buttonSalvar = findViewById(R.id.buttonSalvar);
        Button buttonBuscarCEP = findViewById(R.id.buttonBuscarCEP);

        // Configurar o listener de clique para o botão de salvar
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });

        // Configurar o listener de clique para o botão de buscar CEP
        buttonBuscarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCEP();
            }
        });
    }

    private void buscarCEP() {
        // Dentro do método onCreate de CadastroAlunoActivity
        String cep = editTextCEP.getText().toString();
        ViaCEPService service = new ViaCEPService();
        service.buscarCEP(cep, this, new ViaCEPService.ViaCEPResponseListener() {
            @Override
            public void onCEPSuccess(ViaCEPResponse response) {
                // Verificar se a consulta foi bem sucedida
                if (response != null) {
                    // Atualizar os campos de endereço com os dados obtidos
                    editTextLogradouro.setText(response.getLogradouro());
                    editTextComplemento.setText(response.getComplemento());
                    editTextBairro.setText(response.getBairro());
                    editTextCidade.setText(response.getLocalidade());
                    editTextUF.setText(response.getUF());
                } else {
                    // Mostrar uma mensagem de erro caso a consulta falhe
                    Toast.makeText(CadastroAlunoActivity.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCEPError(String error) {
                // Mostrar uma mensagem de erro caso ocorra um erro na consulta
                Toast.makeText(CadastroAlunoActivity.this, "Erro ao consultar CEP: " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void salvarAluno() {
        int ra = Integer.parseInt(editTextRA.getText().toString());
        String nome = editTextNome.getText().toString();
        String cep = editTextCEP.getText().toString();
        String logradouro = editTextLogradouro.getText().toString();
        String complemento = editTextComplemento.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String uf = editTextUF.getText().toString();

        Aluno aluno = new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf);

        MockAPIUtil.salvarAluno(aluno, new MockAPIUtil.OnSaveAlunoListener() {
            @Override
            public void onSaveAlunoComplete(boolean success) {
                if (success) {
                    Toast.makeText(CadastroAlunoActivity.this, "Aluno salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    editTextRA.setText("");
                    editTextNome.setText("");
                    editTextCEP.setText("");
                    editTextLogradouro.setText("");
                    editTextComplemento.setText("");
                    editTextBairro.setText("");
                    editTextCidade.setText("");
                    editTextUF.setText("");
                } else {
                    Toast.makeText(CadastroAlunoActivity.this, "Falha ao salvar aluno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

