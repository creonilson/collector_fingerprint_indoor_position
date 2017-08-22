package indoor.creonilso.com.br.coletadadosindoor.view.mvpview;

/**
 * Created by User on 22/08/2017.
 */

public interface IHomeView extends IMvpView {
    void mostrarMensagem(String mensagem);

    void habilitarBotao();

    void setBtnMedirTexto(String texto);
}
