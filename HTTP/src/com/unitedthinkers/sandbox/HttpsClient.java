package com.unitedthinkers.sandbox;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpsClient {

    public HttpsClient(){
    }

    public String getContentOfTheUrl(String httpsUrl){
        String result = "";
        try {
            URL url = new URL(httpsUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input;

            while ((input = br.readLine()) != null){
                result += input;
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getFieldResponse(String fieldResponse, String fullResponse){
        String result = fullResponse.substring(fullResponse.indexOf(fieldResponse) + fieldResponse.length());
        return result.substring(0, result.indexOf("&"));
    }

    public void printFullResponse(String fullResponse){
        for (String line : fullResponse.split("&")){
            System.out.println(line);
        }
    }
}
