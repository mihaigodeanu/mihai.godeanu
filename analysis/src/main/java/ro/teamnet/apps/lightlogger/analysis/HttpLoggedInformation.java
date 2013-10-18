package ro.teamnet.apps.lightlogger.analysis;

import ro.teamnet.apps.lightlogger.api.LoggedInformation;

/**
 * @author dan.cioiu
 * @since 10/7/13
 */
public class HttpLoggedInformation implements LoggedInformation{

    private HttpMethod type;
    private String value;
    private int hits;

    public HttpLoggedInformation(HttpMethod type, String value, int hits) {
        this.type = type;
        this.value = value;
        this.hits = hits;
    }

    @Override
    public String getType() {
        return type.toString();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getHits() {
        return hits;
    }

    @Override
    public String toString(){
        return type + " " + value + " " + hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpLoggedInformation that = (HttpLoggedInformation) o;

        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
