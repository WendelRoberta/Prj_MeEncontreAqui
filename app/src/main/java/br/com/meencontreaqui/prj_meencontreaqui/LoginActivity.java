package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.meencontreaqui.prj_meencontreaqui.database.DbAdapter;
import br.com.meencontreaqui.prj_meencontreaqui.entidades.Usuarios;

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
        String usuario = tUsuario.getText().toString();
        String senha = tSenha.getText().toString();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void proximaTela(View view) {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }
    public void proximaTelaPrincipal(View view) {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
    }

}
