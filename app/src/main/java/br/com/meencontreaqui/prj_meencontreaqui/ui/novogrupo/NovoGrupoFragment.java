package br.com.meencontreaqui.prj_meencontreaqui.ui.novogrupo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.meencontreaqui.prj_meencontreaqui.R;

public class NovoGrupoFragment extends Fragment {

    private NovoGrupoViewModel novoGrupoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        novoGrupoViewModel =
                ViewModelProviders.of(this).get(NovoGrupoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_novogrupo, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        novoGrupoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}