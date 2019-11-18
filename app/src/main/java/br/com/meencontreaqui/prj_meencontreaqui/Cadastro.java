package br.com.meencontreaqui.prj_meencontreaqui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import br.com.meencontreaqui.prj_meencontreaqui.R;


public class Cadastro extends AppCompatActivity {

    //criação de variáveis
    private EditText cdusername;
    private EditText cdpassword;
    private EditText cdconfpassword;
    private Button btncadastrar;



  //  RequestQueue requestQueue = Volley.newRequestQueue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context mcontext = getApplicationContext();
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cdusername = findViewById(R.id.cdusername);
        cdpassword = findViewById(R.id.cdpassword);
        cdconfpassword = findViewById(R.id.cdconfpassword);
        btncadastrar = findViewById(R.id.btncadastrar);

        URL url = new URL("http://pokeapi.co/api/v2/pokemon/1/");
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {

        //ação a ser executada quando clicar o botão cadastrar
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //validação dos campos de senha e confirmação de senha
                if (cdusername.getText().toString().equals("") ||
                        cdpassword.getText().toString().equals("") ||
                        cdconfpassword.getText().toString().equals("")) {
                    //mensagem de espaço de senha vazio
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else if (!cdpassword.getText().toString().equals(cdconfpassword.getText().toString())) {
                        //mensagem caso as senhas sejam diferentes
                        Toast.makeText(getApplicationContext(), "Senhas diferentes", Toast.LENGTH_SHORT).show();
                    }
                 else {
                    try {
                        User retorno = new HttpService(cdusername.getText().toString(), cdpassword.getText().toString()).execute().get();
                        Toast.makeText(getApplicationContext(), "Usuário adicionado!", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


        /*String URL = "http://localhost:8080/api/user/create";
        //("name": cdusername.getText().toString(), "password": cdpassword.getText().toString());
        try {
            JSONObject jsonuser = new JSONObject();
            jsonuser.put("name", cdusername.getText());
            jsonuser.put("password", cdpassword.getText());
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                URL,
                jsonuser,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Sucesso!", Toast.LENGTH_SHORT).show();

                            JSONArray jsonImages = null;
                            try {
                                jsonImages = response.getJSONArray("images");

                                for(int i =0; i < jsonImages.length(); i++) {
                                    JSONObject jsonImage = jsonImages.getJSONObject(i);
                                    String name = jsonImage.getString("name");
                                    String password = jsonImage.getString("password");

                                    User user = new User(name, password);
                                    Log.e("user", user.toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);
        }catch (Exception i){
            i.printStackTrace();
        }
                */}
            }

        });
    }

}


//a caixa de alerta para se precisarmos:
//AlertDialog.Builder builder = new AlertDialog.Builder(Cadastro.this);
// builder.setTitle("Senhas diferentes");
//builder.setMessage("Confirme se as senhas inseridas são iguais!");
// AlertDialog alerta = builder.create();
// alerta.show();

