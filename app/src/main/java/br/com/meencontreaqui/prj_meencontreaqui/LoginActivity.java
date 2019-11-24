package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import org.json.JSONException;

import java.io.IOException;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button entrar;

    FusedLocationProviderClient mFusedLocationClient;
    LocationRequest mLocationRequest;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mFusedLocationClient = getFusedLocationProviderClient(this);

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
                } else{
                    String nome = username.getText().toString();
                    String senha = password.getText().toString();
                    User user = new User(nome, senha);
                    this.login(user);

                }
            }
            protected void startLocationUpdates() {

                // Create the location request to start receiving updates
                mLocationRequest = new LocationRequest();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(15000);
                mLocationRequest.setFastestInterval(1000);

                // Create LocationSettingsRequest object using location request
                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
                builder.addLocationRequest(mLocationRequest);
                LocationSettingsRequest locationSettingsRequest = builder.build();
                locationSettingsRequest

                // Check whether location settings are satisfied
                // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
                SettingsClient settingsClient = LocationServices.getSettingsClient(this);
                settingsClient.checkLocationSettings(locationSettingsRequest);

                // new Google API SDK v11 uses getFusedLocationProviderClient(this)
                getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                // do work here
                                onLocationChanged(locationResult.getLastLocation());
                            }
                        },
                        Looper.myLooper());
            }

            public void login(User user) {
                try {
                    UserResources userResources = new UserResources();
                    mFusedLocationClient.getLastLocation();
                    User response = null;


                    response = userResources.login(user);


                    if(response.getName().equalsIgnoreCase(user.getName()) && response.getPassword().equalsIgnoreCase(user.getPassword())){
                        Toast.makeText(getApplicationContext(), "Login efetuado", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Principal.class);
                        i.putExtra("UserName",response.getName());
                        i.putExtra("Password",response.getPassword());
                        startActivity(i);

                    }

                    while (mFusedLocationClient.getLastLocation().getResult().getLongitude()==0.0){
                        Thread.sleep(3000);
                    }
                    user.setLatitude(mFusedLocationClient.getLastLocation().getResult().getLatitude());
                    user.setLongitude(mFusedLocationClient.getLastLocation().getResult().getLongitude());
                    user.setActive(1);
                    userResources.updateState(user);


                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro em fazer login!");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        }
}
