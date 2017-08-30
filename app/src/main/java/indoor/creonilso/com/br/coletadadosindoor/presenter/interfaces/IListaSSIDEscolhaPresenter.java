package indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces;

import java.util.List;

/**
 * Created by User on 30/08/2017.
 */

public interface IListaSSIDEscolhaPresenter extends IPresenter {
    void salvarSSIEscolhido(List<String> selecionados);

    void pegarSSIEscolhido();
}
