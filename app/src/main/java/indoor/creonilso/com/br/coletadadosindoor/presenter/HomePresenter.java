package indoor.creonilso.com.br.coletadadosindoor.presenter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.model.WifiFingerprint;
import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IHomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.util.JsonFileUtils;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IHomeView;

/**
 * Created by User on 22/08/2017.
 */

public class HomePresenter implements IHomePresenter {

    private IHomeView homeView;
    private List<ScanResult> mWifiList;
    private boolean isColetando;
    private List<WifiFingerprint> mFingerprints;
    public static final int NUMERO_DE_MEDIDAS = 100;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onCreate() {
        WifiManager wifiManager = (WifiManager) homeView.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiList = wifiManager.getScanResults();
        mFingerprints = new ArrayList<>();
    }

    @Override
    public void coletarDados(final String localAtual){
        mFingerprints.clear();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            int contVezes = 0;
            @Override
            public void run() {
                while (contVezes < NUMERO_DE_MEDIDAS) {
                    contVezes ++;
                    for (ScanResult scanResult : mWifiList) {
                        if (scanResult.SSID.equals("WIVIA") || scanResult.SSID.equals("IVIA.BNB") || scanResult.SSID.equals("IVIA@Visitante")) {
                            WifiFingerprint fingerprint = new WifiFingerprint();
                            fingerprint.setSSID(scanResult.SSID);
                            fingerprint.setRSSI(scanResult.level);
                            fingerprint.setLevel(WifiManager.calculateSignalLevel(scanResult.level, 5));
                            fingerprint.setLabel(localAtual);
                            mFingerprints.add(fingerprint);
                        }
                    }
                }

                salvarDadosRssi("");
            }
        }, 500);
    }

    @Override
    public void salvarDadosRssi(String localAtual) {
        try {
            JsonFileUtils.writeFingerprintJsonStream(mFingerprints);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            homeView.mostrarMensagem("Arquivo salvo");
            homeView.habilitarBotao();
            homeView.setBtnMedirTexto("Medir");
        }
    }
}
