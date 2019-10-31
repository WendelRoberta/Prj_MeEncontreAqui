package br.com.meencontreaqui.prj_meencontreaqui.ui.novoamigo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NovoAmigoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NovoAmigoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}