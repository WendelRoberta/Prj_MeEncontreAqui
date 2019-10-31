package br.com.meencontreaqui.prj_meencontreaqui.ui.minhaconta;

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

public class MinhaContaFragment extends Fragment {

    private MinhaContaViewModel minhaContaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        minhaContaViewModel =
                ViewModelProviders.of(this).get(MinhaContaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_minhaconta, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        minhaContaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}