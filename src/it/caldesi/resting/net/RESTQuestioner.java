package it.caldesi.resting.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public abstract class RESTQuestioner {

    protected Response doRequest(String urlString, Map<String, String> params, RequestMethod requestMethod) {
        Response response = null;
        String urlParameters = buildParamsString(params);
        String method = requestMethod.getValue();
        HttpURLConnection connection = null;
        try {
            // Create connection
            if (method.equals("GET"))
                urlString += "?" + urlParameters;
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            if (method.equals("POST")) {
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length",
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
            }

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            if (method.equals("POST")) {
                // Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.close();
            }

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringResponse = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                stringResponse.append(line);
                stringResponse.append('\r');
            }
            rd.close();
            response = new Response(stringResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    protected String buildParamsString(Map<String, String> params) {
        StringBuilder paramsString = new StringBuilder();
        for (String paramName : params.keySet()) {
            appendParam(paramsString, paramName, params.get(paramName));
        }
        return paramsString.toString();
    }

    protected void appendParam(StringBuilder paramsString, String paramName, String param) {
        if (param != null && param.length() > 0) {
            if (paramsString.length() > 0)
                paramsString.append('&');
            paramsString.append(paramName);
            paramsString.append('=');
            paramsString.append(encodeParam(param));
        }
    }

    protected String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return param;
        }
    }

}