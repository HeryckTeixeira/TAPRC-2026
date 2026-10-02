package com.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class HttpTriggerJava1 {
    
    @FunctionName("HttpTriggerJava1")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String name = request.getQueryParameters().get("name");

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Informe um parâmetro na URL. Exemplo: /api/HttpTriggerJava1?name=Fernanda").build();
        } else {
            context.getLogger().info("Parâmetro recebido: " + name);
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "text/plain; charset=utf-8")
                    .body("Parâmetro retornado: " + name + " - function httptriggerjava1.")
                    .build();
        }
    }
}