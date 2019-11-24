package br.com.meencontreaqui.prj_meencontreaqui.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import br.com.meencontreaqui.prj_meencontreaqui.R;

import static br.com.meencontreaqui.prj_meencontreaqui.R.layout.fragment_inicio;

public class InicioFragment extends Fragment  {

    private InicioViewModel inicioViewModel;
    private FragmentManager fragmentManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel =
                ViewModelProviders.of(this).get(InicioViewModel.class);
        View root = inflater.inflate(fragment_inicio, container, false);

        fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framemapa, new MapsFragment(), "MapsFragment");
        transaction.commitAllowingStateLoss();

        return root;
    }
}