package indoor.creonilso.com.br.coletadadosindoor.presenter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.R;
import indoor.creonilso.com.br.coletadadosindoor.model.WifiFingerprint;
import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IHomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.util.JsonFileUtils;
import indoor.creonilso.com.br.coletadadosindoor.util.SharedPreferencesUtil;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IHomeView;

/**
 * Created by User on 22/08/2017.
 */

public class HomePresenter implements IHomePresenter {

    private IHomeView mHomeView;
    private List<ScanResult> mWifiList;
    private boolean isColetando;
    private List<WifiFingerprint> mFingerprints;
    private List<String> mSsidSeleionados;
    private static final int NUMERO_DE_MEDIDAS = 10;

    public HomePresenter(IHomeView homeView) {
        this.mHomeView = homeView;
    }

    @Override
    public void onCreate() {
        Context context = mHomeView.getContext();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiList = wifiManager.getScanResults();
        mFingerprints = new ArrayList<>();
        mSsidSeleionados = new SharedPreferencesUtil(context).pegarLista(ListaSSIDEscolhaPresenter.KEY_ESCOLHIDOS);
    }

    @Override
    public void coletarDados(final String localAtual){
        mFingerprints.clear();
        if(mSsidSeleionados.isEmpty()){
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            int contVezes = 0;
            @Override
            public void run() {
                while (contVezes < NUMERO_DE_MEDIDAS) {
                    contVezes ++;
                    for (ScanResult scanResult : mWifiList) {
                        if (mSsidSeleionados.contains(scanResult.SSID)) {
                            WifiFingerprint fingerprint = new WifiFingerprint();
                            fingerprint.setSSID(scanResult.SSID);
                            fingerprint.setRSSI(scanResult.level);
                            fingerprint.setLevel(WifiManager.calculateSignalLevel(scanResult.level, 5));
                            fingerprint.setLabel(localAtual);
                            mFingerprints.add(fingerprint);
                        }
                    }
                }

                salvarDadosRssi();
            }
        }, 500);
    }

    @Override
    public void salvarDadosRssi() {
        try {
            JsonFileUtils.writeFingerprintJsonStream(mFingerprints);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Context context = mHomeView.getContext();
            mHomeView.mostrarMensagem(context.getString(R.string.txt_arquivo_salvo_armazenamento_interno));
            mHomeView.habilitarBotao();
            mHomeView.setBtnMedirTexto(context.getString(R.string.coletar_dados));
        }
    }
}
