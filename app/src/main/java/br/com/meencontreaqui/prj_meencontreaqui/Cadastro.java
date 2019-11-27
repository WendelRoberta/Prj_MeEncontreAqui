/**
 * Projeto de CPDM
 * Nome: Me Encontre Aqui
 * Data: 27/11/2019
 * Autores: Aaban Vasconcelos; Luana de Sá; Thalita Barros; Wendel Roberta
 * Professor: Renan Alencar
 */
package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;


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


        final TextView resposta = findViewById(R.id.tcadastro);



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
                    String nome = cdusername.getText().toString();
                    String senha = cdconfpassword.getText().toString();
                    User user = new User(nome, senha);
                    this.inserirContato(user);
                }
            }
            public void inserirContato(User user) {

                UserResources userResources = new UserResources();
                boolean success = false;

                try {
                    user.setLatitude(-8.3561477);
                    user.setLongitude(-34.8981457);
                    success = userResources.insertUser(user);

                    if(success){
                        Toast.makeText(getApplicationContext(), "Usuário criado com sucesso", Toast.LENGTH_SHORT).show();
                        cdusername.setText("");
                        cdpassword.setText("");
                        cdconfpassword.setText("");


                    }

                    //  userResources.insertUser(new User("bbb", "ccc"));

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro em inserir!");

                    //JOGA O ERRO NO USUARIO E SENHA  PRECISA SER REFEITO
                    cdusername.setText("ERRO");
                    cdpassword.setText("");
                    cdconfpassword.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
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

