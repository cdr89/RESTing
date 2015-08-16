package it.caldesi.resting.net;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public enum RequestMethod {
    POST("POST"), GET("GET");

    private String value;

    public String getValue() {
        return value;
    }

    private RequestMethod(String value) {
        this.value = value;
    }
}