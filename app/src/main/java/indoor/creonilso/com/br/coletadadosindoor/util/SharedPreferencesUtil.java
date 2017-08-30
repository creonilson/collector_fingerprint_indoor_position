package indoor.creonilso.com.br.coletadadosindoor.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by User on 30/08/2017.
 */

public class SharedPreferencesUtil {

    private static final String PREF_NAME = "ColetorAPP";
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void salvarLista(String key, List list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mSharedPreferences.edit().putString(key, json).apply();
    }

    public List pegarLista(String key){
        Gson gson = new Gson();
        String stringArmazenada = mSharedPreferences.getString(key, "[]");
        return gson.fromJson(stringArmazenada, List.class);
    }
}
