package br.com.meencontreaqui.prj_meencontreaqui.ui.inicio;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.meencontreaqui.prj_meencontreaqui.User;
import br.com.meencontreaqui.prj_meencontreaqui.UserResources;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    //@+id/switchlocalizacao"
   // UserResources userRes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            UserResources userRes = new UserResources();
           List<User> lista = userRes.getUsers();
            Log.d("lista tostring:", lista.toString());

            for (User user: lista
                 ) {

                if(user.getActive() == 1){
                    Log.d("For do mapa", user.toString());
                    MarkerOptions marker = new MarkerOptions();
                   Double lati = user.getLatitude();
                   Double longi = user.getLongitude();
                    Log.d("latilonge", lati.toString()+longi.toString());

                    LatLng userLocation = new LatLng(lati,longi);
                    marker.position(userLocation);
                    marker.title(user.getName());
                    mMap = googleMap;
                    mMap.setMyLocationEnabled(true);
                    mMap.getUiSettings().setZoomControlsEnabled(true);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));

                    // Add a marker in Sydney and move the camera
                      mMap.addMarker(new MarkerOptions().position(userLocation).title(user.getName()));
                      mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));



                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
