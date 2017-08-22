package indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces;

/**
 * Created by User on 22/08/2017.
 */

public interface IHomePresenter extends IPresenter {
    void coletarDados(String localAtual);

    void salvarDadosRssi(String localAtual);
}
