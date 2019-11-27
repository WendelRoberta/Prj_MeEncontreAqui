/**
 * Projeto de CPDM
 * Nome: Me Encontre Aqui
 * Data: 27/11/2019
 * Autores: Aaban Vasconcelos; Luana de Sá; Thalita Barros; Wendel Roberta
 * Professor: Renan Alencar
 */
package br.com.meencontreaqui.prj_meencontreaqui.ui.inicio;

import android.widget.ScrollView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.meencontreaqui.prj_meencontreaqui.R;

public class InicioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InicioViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}