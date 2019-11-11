package br.com.meencontreaqui.prj_meencontreaqui.ui.inicio;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import br.com.meencontreaqui.prj_meencontreaqui.Principal;
import br.com.meencontreaqui.prj_meencontreaqui.R;

import static br.com.meencontreaqui.prj_meencontreaqui.R.layout.fragment_inicio;
import br.com.meencontreaqui.prj_meencontreaqui.ui.inicio.InicioFragment;

public class InicioFragment extends Fragment  {

    private InicioViewModel inicioViewModel;
    private FragmentManager fragmentManager;

    Principal scrollView;
    TextView saudacao;

    Principal mapView;
    GoogleMap map;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel =
                ViewModelProviders.of(this).get(InicioViewModel.class);
        View root = inflater.inflate(fragment_inicio, container, false);

        /*final ScrollView sv = (ScrollView) inflater.inflate(R.layout.fragment_inicio, container, false);
        scrollView = Principal.class.cast(sv.findViewById(R.id.idscrollview));
        return sv;

        final View mv = (View) inflater.inflate(R.layout.fragment_inicio, container, false);
        mapView = Principal.class.cast(mv.findViewById(R.id.idmapView));
        mapView.onCreate(savedInstanceState);

        if (mapView != null) {
            map = mapView.getMap();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setAllGesturesEnabled(true);
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        }*/

        fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framemapa, new MapsFragment(), "MapsFragment");
        transaction.commitAllowingStateLoss();

        return root;
    }
}