package br.com.meencontreaqui.prj_meencontreaqui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.meencontreaqui.prj_meencontreaqui.R;


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
                    Toast.makeText(getApplicationContext(), "Usuário adicionado!", Toast.LENGTH_SHORT).show();
                }

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

