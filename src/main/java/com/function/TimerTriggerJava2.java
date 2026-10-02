package com.function;

import java.time.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TimerTriggerJava2 {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @FunctionName("TimerTriggerJava2")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());

        String baseUrl = System.getenv("FUNCTION_BASE_URL");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = "http://localhost:7071";
        }

        String informacao = "Enviado pelo TimerTriggerJava2";
        String url = baseUrl + "/api/HttpTriggerJava1?name=" + URLEncoder.encode(informacao, StandardCharsets.UTF_8);
        context.getLogger().info("Chamando: " + url);

        try {
            HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> resposta = HTTP_CLIENT.send(requisicao, HttpResponse.BodyHandlers.ofString());
            context.getLogger().info("Status: " + resposta.statusCode() + " | Resposta: " + resposta.body());
        } catch (Exception e) {
            context.getLogger().severe("Erro ao chamar a HttpTriggerJava1: " + e.getMessage());
        }
    }
}