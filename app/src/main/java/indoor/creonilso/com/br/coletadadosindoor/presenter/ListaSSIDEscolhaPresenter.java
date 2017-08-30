package indoor.creonilso.com.br.coletadadosindoor.presenter;

import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IListaSSIDEscolhaPresenter;
import indoor.creonilso.com.br.coletadadosindoor.util.SharedPreferencesUtil;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IListaSSIDEscolhaView;

/**
 * Created by User on 30/08/2017.
 */

public class ListaSSIDEscolhaPresenter implements IListaSSIDEscolhaPresenter {

    private static final String KEY_ESCOLHIDOS = "KEY_ESCOLHIDOS";
    private IListaSSIDEscolhaView mListaSSIDEscolhaView;
    private SharedPreferencesUtil mSharedPreferencesUtil;

    public ListaSSIDEscolhaPresenter(IListaSSIDEscolhaView listaSSIDEscolhaView) {
        this.mListaSSIDEscolhaView = listaSSIDEscolhaView;
    }

    @Override
    public void onCreate() {
       mSharedPreferencesUtil = new SharedPreferencesUtil(mListaSSIDEscolhaView.getContext());
    }

    @Override
    public void salvarSSIEscolhido(List<String> selecionados) {
        mSharedPreferencesUtil.salvarLista(KEY_ESCOLHIDOS, selecionados);
    }

    @Override
    public void pegarSSIEscolhido(){
        mSharedPreferencesUtil.pegarLista(KEY_ESCOLHIDOS);
    }
}
