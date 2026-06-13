package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.concurrent.TickEndExecutor;
import com.dimsteams.sodiumpp.scripting.AdvancedApi;
import com.dimsteams.sodiumpp.scripting.types.HttpRequestWrapper;
import com.dimsteams.sodiumpp.scripting.types.HttpResponseWrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@AdvancedApi
public class HttpApi {

    private final HttpClient client = HttpClient.newHttpClient();

    public CompletableFuture<String> get(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            return CompletableFuture.completedFuture("<INVALID URI>");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return future.thenApplyAsync(HttpResponse::body, TickEndExecutor.instance);
    }

    public CompletableFuture<HttpResponseWrapper> send(HttpRequestWrapper request) {
        if (request == HttpRequestWrapper.INVALID) {
            return CompletableFuture.completedFuture(HttpResponseWrapper.INVALID_REQUEST);
        }

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request.getRequest(), HttpResponse.BodyHandlers.ofString());
        return future.thenApplyAsync(HttpResponseWrapper::new, TickEndExecutor.instance);
    }
}