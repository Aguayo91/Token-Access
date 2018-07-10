

public String getToken(String nameService) {

    HttpURLConnection con = null;

    String token = null;
    String fecha = "";
    String uri = "";
    String ip = "";
    String idApp = "";
    boolean error = false;

    try {

        SimpleDateFormat fechaSDF = new SimpleDateFormat("ddMMyyyyHHmmss");
        fechaSDF.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        fecha = "fecha=" + fechaSDF.format(new Date());

        Log.v("fecha","fecha mexico: "+fecha);

        uri = "uri=" + FRQConstantes.URL_WEB_SERVICES + "/" + nameService;
        ip = "ip=" + getLocalIpAddress();
        idApp = "idapl=" + FRQConstantes.IDAPP;

        String cadena = idApp + "&"+ UtilCryptoGS.encrypt(ip + "&" + fecha + "&"  + uri, FRQConstantes.MASTERKEY_ENCRYPT).replace("\n", "");
        Log.v(TAG,"CADENA = "+cadena);

        URL url = new URL(FRQConstantes.URL_WEB_SERVICES_TOKEN + "/" + FRQConstantes.WS_GETTOKEN + "?" + cadena);

        Log.v(TAG, "URL = " + FRQConstantes.URL_WEB_SERVICES_TOKEN + "/" + FRQConstantes.WS_GETTOKEN + "?" + cadena);

        if(FRQConstantes.desarrollo) {
            con = (HttpURLConnection) url.openConnection();

        }else{
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            SSLContext cont = SSLContext.getInstance("TLS");
            cont.init(null, new X509TrustManager[]{new NullX509TrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(cont.getSocketFactory());

            con = (HttpURLConnection) url.openConnection();
        }

        // Obtener el estado del recurso
        int statusCode = con.getResponseCode();

        if (statusCode != 200) {
            error = true;
        } else {
            InputStream in = new BufferedInputStream(con.getInputStream());

            Convert c = new Convert();

            Log.v(TAG, "in = " + in);

            String aux = c.getStringFromInputStream(in);

            Log.v(TAG, "aux = " + aux);


            JSONObject object = new JSONObject(aux);
            token = object.get("token").toString();

            Log.v(TAG, "token = " + token);

            if (token != null && token.contains(FRQConstantes.ERROR_TOKEN)) {
                token = null;
            }

        }
    } catch (Exception e) {
        Log.v(TAG, "Error al convertir Token JSON " + e.getMessage());
        token = null;
    }

    return token;

}
