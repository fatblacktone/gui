/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stocktrader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 *
 * @author tone_
 */
public class APIRequest {
    
    private static final String ALPHA_VANTAGE_API_KEY = "MO2L5FP6K62D4HGJ";
    private static final String SYMBOL = "IBM"; // You can change this to the stock symbol you want
    
    
    public void AlphaVantageAPICall(){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.alphavantage.co/query"
                        + "?function=OVERVIEW&symbol=" + SYMBOL
                        + "&apikey=" + ALPHA_VANTAGE_API_KEY))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
