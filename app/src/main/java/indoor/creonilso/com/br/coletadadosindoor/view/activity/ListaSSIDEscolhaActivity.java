package indoor.creonilso.com.br.coletadadosindoor.view.activity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.R;
import indoor.creonilso.com.br.coletadadosindoor.presenter.ListaSSIDEscolhaPresenter;
import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IListaSSIDEscolhaPresenter;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IListaSSIDEscolhaView;

public class ListaSSIDEscolhaActivity extends AppCompatActivity implements View.OnClickListener, IListaSSIDEscolhaView {

    private ListView mListSelecaoWifi;
    private List<String> mListaWifi;
    private IListaSSIDEscolhaPresenter mSsidEscolhaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ssidescolha);

        mSsidEscolhaPresenter = new ListaSSIDEscolhaPresenter(this);
        mSsidEscolhaPresenter.onCreate();

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = wifiManager.getScanResults();
        mListaWifi = new ArrayList<>();

        for (ScanResult scanResult : scanResults) {
            mListaWifi.add(scanResult.SSID);
        }

        mListSelecaoWifi = (ListView) findViewById(R.id.list_selecao_wifi);
        Button mBtnConfirmar = (Button) findViewById(R.id.btn_ok);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked);
        arrayAdapter.addAll(mListaWifi);
        mListSelecaoWifi.setAdapter(arrayAdapter);
        mListSelecaoWifi.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mBtnConfirmar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ok){
            List<String> selecionados = new ArrayList<>();
            SparseBooleanArray sparseBooleanArray = mListSelecaoWifi.getCheckedItemPositions();
            for (int indice = 0; indice < mListaWifi.size(); indice++){
                if(sparseBooleanArray.get(indice)){
                    selecionados.add(mListaWifi.get(indice));
                }
            }
            mSsidEscolhaPresenter.salvarSSIEscolhido(selecionados);
        }
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
