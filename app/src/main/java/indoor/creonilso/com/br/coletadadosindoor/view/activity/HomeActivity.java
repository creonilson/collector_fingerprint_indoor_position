package indoor.creonilso.com.br.coletadadosindoor.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import indoor.creonilso.com.br.coletadadosindoor.R;
import indoor.creonilso.com.br.coletadadosindoor.presenter.HomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.presenter.interfaces.IHomePresenter;
import indoor.creonilso.com.br.coletadadosindoor.view.mvpview.IHomeView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IHomeView {

    private Button mBtnMedir;
    private EditText mEdtLabel;
    private IHomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBtnMedir = (Button) findViewById(R.id.btn_medir);
        mEdtLabel = (EditText) findViewById(R.id.edt_label);

        mBtnMedir.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mHomePresenter = new HomePresenter(this);
        mHomePresenter.onCreate();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_medir) {
            if(mEdtLabel.getText().toString().isEmpty()){
                Toast.makeText(this, "Adicione um label para o local de coleta", Toast.LENGTH_LONG).show();
            }else {
                mBtnMedir.setText(R.string.txt_coletando);
                mBtnMedir.setEnabled(false);
                mHomePresenter.coletarDados(mEdtLabel.getText().toString());
            }
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
                startActivity(new Intent(this, ListaSSIDEscolhaActivity.class));
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
        mBtnMedir.setEnabled(true);
    }

    @Override
    public void setBtnMedirTexto(String texto) {
        mBtnMedir.setText(texto);
    }
}
