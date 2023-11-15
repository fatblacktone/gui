/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.angryandroid.m1cha3l;

import java.io.IOException;
import okhttp3.*;

public class ChatGpt {

    private static final String API_KEY = "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";  // Replace with your own API key
    private static final String URL = "https://chat.openai.com/v1/chat/completions";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, 
                "{\n" +
                        "  \"model\": \"gpt-3.5-turbo\",\n" +
                        "  \"prompt\": \"Translate the following English text to French: 'Hello, how are you?'\",\n" +
                        "  \"temperature\": 0.7,\n" +
                        "  \"max_tokens\": 60\n" +
                        "}"
        );

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}