package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button entrar;

    FusedLocationProviderClient mFusedLocationClient;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setContentView(R.layout.activity_login);
        Button entrar = (Button) findViewById(R.id.entrar);
        Button cadastrar = (Button) findViewById(R.id.cadastrar);
        TextView tBemvindo = (TextView) findViewById(R.id.bemvindo);
        final EditText tUsuario = (EditText) findViewById(R.id.username);
        EditText tSenha = (EditText) findViewById(R.id.password);
        String bemvindo = tBemvindo.getText().toString();
        final String usuario = tUsuario.getText().toString();
        String senha = tSenha.getText().toString();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(i);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("") ||
                        password.getText().toString().equals("")) {
                    //mensagem de espaço de senha vazio
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else{

                    String nome = username.getText().toString();
                    String senha = password.getText().toString();
                    User user = new User(nome, senha);
                    this.login(user);

                }
            }

            public void login(User user) {
                try {
                    UserResources userResources = new UserResources();
                    User response = null;

                    response = userResources.login(user);
                    System.out.println(response.getName()+ response.getPassword()+"===========================================================================");
                    if(1==1){
                        Toast.makeText(getApplicationContext(), "Usuário criado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Principal.class);
                        i.putExtra("name", "Olá, "+tUsuario.getText().toString()+"!");
                        startActivity(i);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro em fazer login!");
                }
            }
        });
        }
}
