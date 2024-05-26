package com.example.ac2_prova;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ViaCEPService {

    public interface ViaCEPResponseListener {
        void onCEPSuccess(ViaCEPResponse response);
        void onCEPError(String error);
    }

    public void buscarCEP(String cep, Context context, ViaCEPResponseListener listener) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // Criar uma solicitação GET para o URL do ViaCEP
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Supondo que você já tenha obtido os valores corretos para logradouro, complemento, bairro, cidade e uf
                        String logradouro = "Rua Exemplo";
                        String complemento = "Apto 101";
                        String bairro = "Centro";
                        String cidade = "São Paulo";
                        String uf = "SP";

                        // Processar a resposta JSON do ViaCEP
                        ViaCEPResponse viaCEPResponse = new ViaCEPResponse(logradouro, complemento, bairro, cidade, uf);
                        viaCEPResponse.parseJSON(response);

                        // Chamar o listener com a resposta do ViaCEP
                        listener.onCEPSuccess(viaCEPResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Notificar o listener sobre o erro
                        listener.onCEPError(error.getMessage());
                    }
                });

        // Adicionar a requisição à fila de requisições
        Volley.newRequestQueue(context).add(request);
    }

}

