/**
 * Projeto de CPDM
 * Nome: Me Encontre Aqui
 * Data: 27/11/2019
 * Autores: Aaban Vasconcelos; Luana de Sá; Thalita Barros; Wendel Roberta
 * Professor: Renan Alencar
 */
package br.com.meencontreaqui.prj_meencontreaqui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;

import br.com.meencontreaqui.prj_meencontreaqui.ui.inicio.MapsFragment;



public class Principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String nomeusuario;
    private FragmentManager fragmentManager;
    FusedLocationProviderClient mFusedLocationClient;
    private Switch btnswitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView nomeusuario = (TextView) findViewById(R.id.txtusuario);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framemapa, new MapsFragment(), "MapsFragment");
        transaction.commitAllowingStateLoss();

        View header = navigationView.getHeaderView(0);
        nomeusuario = header.findViewById(R.id.txtusuario);

        if (savedInstanceState == null) {
            String getName = getIntent().getStringExtra( "name");
            nomeusuario.setText(getName);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);

        mFusedLocationClient.getLastLocation();


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
