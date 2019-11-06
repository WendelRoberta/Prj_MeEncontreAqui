package br.com.meencontreaqui.prj_meencontreaqui.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.meencontreaqui.prj_meencontreaqui.R;

public class InicioFragment extends Fragment {

    private InicioViewModel inicioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel =
                ViewModelProviders.of(this).get(InicioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        //final ScrollView scrollView = root.findViewById(R.id.idscrollview);
        final TextView textView = root.findViewById(R.id.txtsaudacao);
        inicioViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
               textView.setText(s);
            }
        });
       //final ScrollView scrollView = root.findViewById(R.id.idscrollview);
        //scrollView.addView(root);
        return root;
    }
}