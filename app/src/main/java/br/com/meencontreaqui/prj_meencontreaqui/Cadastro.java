package br.com.meencontreaqui.prj_meencontreaqui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.meencontreaqui.prj_meencontreaqui.R;
import br.com.meencontreaqui.prj_meencontreaqui.database.DbAdapter;
import br.com.meencontreaqui.prj_meencontreaqui.entidades.Usuarios;


public class Cadastro extends AppCompatActivity {

    //criação de variáveis
    private EditText cdusername;
    private EditText cdpassword;
    private EditText cdconfpassword;
    private Button btncadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cdusername = findViewById(R.id.cdusername);
        cdpassword = findViewById(R.id.cdpassword);
        cdconfpassword = findViewById(R.id.cdconfpassword);
        btncadastrar = findViewById(R.id.btncadastrar);
        

        //ação a ser executada quando clicar o botão cadastrar
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //validação dos campos de senha e confirmação de senha
                if (cdpassword.getText().toString().equals(cdconfpassword.getText().toString())){
                    //adicionar usuário ao banco de dados
                    DbAdapter adapter = new DbAdapter(Cadastro.this);
                    Usuarios usuarios = adapter.createUsuarios(cdusername.getText().toString(),
                            cdpassword.getText().toString());
                    //mensagem de usuário cadastrado
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cadastro.this);
                    builder.setTitle("Usuário cadastrado");
                    builder.setMessage("Usuário " + usuarios.getUsername() + " adicionado com sucesso!");
                    AlertDialog alerta = builder.create();
                    alerta.show();
                }else{
                    //mensagem caso as senhas sejam diferentes
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cadastro.this);
                    builder.setTitle("Senhas diferentes");
                    builder.setMessage("Confirme se as senhas inseridas são iguais!");
                    AlertDialog alerta = builder.create();
                    alerta.show();
                }

            }

            });
    }


}

