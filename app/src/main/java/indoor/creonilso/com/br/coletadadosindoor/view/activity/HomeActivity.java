package indoor.creonilso.com.br.coletadadosindoor.view.activity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.R;
import indoor.creonilso.com.br.coletadadosindoor.model.WifiFingerprint;
import indoor.creonilso.com.br.coletadadosindoor.presenter.HomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IHomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.util.JsonFileUtils;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IHomeView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IHomeView {

    private Button btnMedir;
    private EditText edtLabel;
    private IHomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnMedir = (Button) findViewById(R.id.btn_medir);
        edtLabel = (EditText) findViewById(R.id.edt_label);

        btnMedir.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homePresenter = new HomePresenter(this);
        homePresenter.onCreate();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_medir) {
            btnMedir.setText("Coletando ...");
            btnMedir.setEnabled(false);
            homePresenter.coletarDados(edtLabel.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_escolha_aps:
                break;
            case R.id.nav_visualizar_medida:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void habilitarBotao() {
        btnMedir.setEnabled(true);
    }

    @Override
    public void setBtnMedirTexto(String texto) {
        btnMedir.setText(texto);
    }
}
