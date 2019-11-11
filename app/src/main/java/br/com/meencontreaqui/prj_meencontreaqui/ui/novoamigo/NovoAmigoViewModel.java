package br.com.meencontreaqui.prj_meencontreaqui.ui.novoamigo;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.meencontreaqui.prj_meencontreaqui.ui.novogrupo.NovoGrupoFragment;

public class NovoAmigoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NovoAmigoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}