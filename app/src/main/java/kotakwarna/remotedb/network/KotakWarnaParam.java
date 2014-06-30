package kotakwarna.remotedb.network;

import java.io.Serializable;

/**
 * Created by Fajar on 6/28/2014.
 */
public class KotakWarnaParam implements Serializable{

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
