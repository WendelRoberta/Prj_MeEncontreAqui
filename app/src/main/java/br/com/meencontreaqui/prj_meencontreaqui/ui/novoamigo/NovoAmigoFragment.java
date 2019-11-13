package br.com.meencontreaqui.prj_meencontreaqui.ui.novoamigo;

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

public class NovoAmigoFragment extends Fragment {

    private NovoAmigoViewModel novoAmigoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        novoAmigoViewModel =
                ViewModelProviders.of(this).get(NovoAmigoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_novoamigo, container, false);
        return root;
    }
}