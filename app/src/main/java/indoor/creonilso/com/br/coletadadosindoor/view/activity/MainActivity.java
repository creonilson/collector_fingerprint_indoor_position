package indoor.creonilso.com.br.coletadadosindoor.view.activity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.R;
import indoor.creonilso.com.br.coletadadosindoor.model.WifiFingerprint;
import indoor.creonilso.com.br.coletadadosindoor.util.JsonFileUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMedir;
    private EditText edtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMedir = (Button) findViewById(R.id.btn_medir);
        edtLabel = (EditText) findViewById(R.id.edt_label);

        btnMedir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_medir){
            mostrarRssi();
        }
    }

    private void mostrarRssi(){
        List<WifiFingerprint> fingerprints = new ArrayList<>();

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        List<ScanResult> wifiList = wifiManager.getScanResults();
        for (ScanResult scanResult : wifiList) {
            if(scanResult.SSID.equals("WIVIA") || scanResult.SSID.equals("IVIA.BNB") || scanResult.SSID.equals("IVIA@Visitante") ){
                WifiFingerprint fingerprint = new WifiFingerprint();
                fingerprint.setSSID(scanResult.SSID);
                fingerprint.setRSSI(scanResult.level);
                fingerprint.setLevel(WifiManager.calculateSignalLevel(scanResult.level, 5));
                fingerprint.setLabel(edtLabel.getText().toString());
                fingerprints.add(fingerprint);
            }
        }
        try {
            JsonFileUtils.writeFingerprintJsonStream(fingerprints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
