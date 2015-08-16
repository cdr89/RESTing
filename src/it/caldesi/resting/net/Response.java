package it.caldesi.resting.net;

import org.json.JSONObject;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public class Response {

    private String stringResponse;

    public Response() {
    }

    public Response(String stringResponse) {
        setStringResponse(stringResponse);
    }

    public void setStringResponse(String stringResponse) {
        this.stringResponse = stringResponse;
    }

    public String getStringResponse() {
        return stringResponse;
    }

    public JSONObject getJSONResponse() {
        JSONObject jsonObject = new JSONObject(stringResponse);
        return jsonObject;
    }

}