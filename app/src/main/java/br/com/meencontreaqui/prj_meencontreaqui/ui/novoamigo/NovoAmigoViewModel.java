/**
 * Projeto de CPDM
 * Nome: Me Encontre Aqui
 * Data: 27/11/2019
 * Autores: Aaban Vasconcelos; Luana de Sá; Thalita Barros; Wendel Roberta
 * Professor: Renan Alencar
 */
package br.com.meencontreaqui.prj_meencontreaqui.ui.novoamigo;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.meencontreaqui.prj_meencontreaqui.ui.novogrupo.NovoGrupoFragment;

public class NovoAmigoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NovoAmigoViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}