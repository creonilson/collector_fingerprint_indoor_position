package indoor.creonilso.com.br.coletadadosindoor.model;

/**
 * Created by User on 17/08/2017.
 */

public class WifiFingerprint {

    private String label;
    private String SSID;
    private int RSSI;
    private int level;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getRSSI() {
        return RSSI;
    }

    public void setRSSI(int RSSI) {
        this.RSSI = RSSI;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
