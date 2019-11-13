package br.com.meencontreaqui.prj_meencontreaqui.ui.minhaconta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MinhaContaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MinhaContaViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}