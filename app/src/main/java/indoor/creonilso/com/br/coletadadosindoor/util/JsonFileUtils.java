package indoor.creonilso.com.br.coletadadosindoor.util;

import android.os.Environment;

import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import indoor.creonilso.com.br.coletadadosindoor.model.WifiFingerprint;

/**
 * Created by User on 17/08/2017.
 */

public class JsonFileUtils {

    public static void writeFingerprintJsonStream(List<WifiFingerprint> fingerprints) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/resultados.json");
        if(!file.exists()){
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file, true);
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.setIndent("  ");
        writeFingerprintArray(writer, fingerprints);
        writer.close();
        outputStream.close();
    }

    private static void writeFingerprintArray(JsonWriter writer, List<WifiFingerprint> fingerprints) throws IOException {
        writer.beginArray();
        for (WifiFingerprint wifiFingerprint : fingerprints) {
            writeWifiFingerprint(writer, wifiFingerprint);
        }
        writer.endArray();
    }

    private static void writeWifiFingerprint(JsonWriter writer, WifiFingerprint fingerprint) throws IOException {
        writer.beginObject();
        writer.name("label").value(fingerprint.getLabel());
        writer.name("RSSI").value(fingerprint.getRSSI());
        writer.name("BSSID").value(fingerprint.getSSID());
        writer.name("level").value(fingerprint.getLevel());
        writer.endObject();
    }
}
