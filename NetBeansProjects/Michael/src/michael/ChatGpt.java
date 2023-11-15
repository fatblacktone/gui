/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package michael;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGpt {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        httpURLConnection.setDoOutput(true);

        String jsonInputString = "{"
        + "\"model\": \"gpt-4\","
        + "\"messages\": ["
        + "{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
        + "{\"role\": \"user\", \"content\": \"Translate the following English text to French: 'Hello, how are you?'\"}"
        + "],"
        + "\"temperature\": 0.7,"
        + "\"max_tokens\": 150"
        + "}";

        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = httpURLConnection.getResponseCode();
        BufferedReader br;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(), "utf-8"));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        System.out.println(response.toString());
        System.out.println(response.toString().split("\"content\":")[1].split("},\"finish_reason\":")[0]);
    }
}