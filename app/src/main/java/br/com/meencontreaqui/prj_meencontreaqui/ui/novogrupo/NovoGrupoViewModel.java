package br.com.meencontreaqui.prj_meencontreaqui.ui.novogrupo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NovoGrupoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NovoGrupoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}