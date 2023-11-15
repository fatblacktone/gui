/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stocktrader;

/**
 *
 * @author tone_
 */
public class StockTrader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        APIRequest request = new APIRequest();
        request.AlphaVantageAPICall();
    }
    
}
