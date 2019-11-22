package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Cadastro extends AppCompatActivity {

    //criação de variáveis
    private EditText cdusername;
    private EditText cdpassword;
    private EditText cdconfpassword;
    private Button btncadastrar;
    private UserResources userResources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context mcontext = getApplicationContext();
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        cdusername = findViewById(R.id.cdusername);
        cdpassword = findViewById(R.id.cdpassword);
        cdconfpassword = findViewById(R.id.cdconfpassword);
        btncadastrar = findViewById(R.id.btncadastrar);

        URL url = null;
        try {
            url = new URL("https://projetomobile.herokuapp.com/api/users/create");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
                                                    } else if (!cdpassword.getText().toString().equals(cdconfpassword.getText().toString())) {
                                                        //mensagem caso as senhas sejam diferentes
                                                        Toast.makeText(getApplicationContext(), "Senhas diferentes", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        String nome = cdusername.getText().toString();
                                                        String senha = cdconfpassword.getText().toString();
                                                        User user = new User(nome, senha);
                                                        this.inserirContato(user);
                                                        // User retorno = new HttpService(cdusername.getText().toString(), cdpassword.getText().toString()).execute().get();
                                                        Toast.makeText(getApplicationContext(), "Usuário adicionado!", Toast.LENGTH_SHORT).show();


                                                    }
                                                }

                                                public void inserirContato(User user) {

                                                    UserResources userResources = new UserResources();

                                                    try {
                                                        Log.i("============USUARIO :", user.toString());
                                                        userResources.insertUser(user);
                                                        userResources.insertUser(new User("bbb", "ccc"));

                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                        System.out.println("Erro em inserir!");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }

            );

        }
    }
}


//a caixa de alerta para se precisarmos:
//AlertDialog.Builder builder = new AlertDialog.Builder(Cadastro.this);
// builder.setTitle("Senhas diferentes");
//builder.setMessage("Confirme se as senhas inseridas são iguais!");
// AlertDialog alerta = builder.create();
// alerta.show();

