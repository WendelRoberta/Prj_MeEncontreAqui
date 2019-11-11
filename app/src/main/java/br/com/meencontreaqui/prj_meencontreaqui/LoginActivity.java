package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button entrar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button entrar = (Button) findViewById(R.id.entrar);
        Button cadastrar = (Button) findViewById(R.id.cadastrar);
        TextView tBemvindo = (TextView) findViewById(R.id.bemvindo);
        EditText tUsuario = (EditText) findViewById(R.id.username);
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
                } else if (username.getText().toString().equals("root") && password.getText().toString().equals("123")) {
                    Intent i = new Intent(getApplicationContext(), Principal.class);
                    startActivity(i);
                }
            } 
        });
        }
}
