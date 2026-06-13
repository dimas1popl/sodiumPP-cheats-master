package com.dimsteams.sodiumpp.scripting.types;

import com.dimsteams.sodiumpp.scripting.HiddenMethod;
import com.dimsteams.scripting.type.CustomType;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

@CustomType(name = "HttpRequest")
public class HttpRequestWrapper {

    public static final HttpRequestWrapper INVALID;

    private final HttpRequest request;

    static {
        try {
            INVALID = new HttpRequestWrapper(HttpRequest.newBuilder(new URI("http://not-used")).build());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException();
        }
    }

    public HttpRequestWrapper(HttpRequest request) {
        this.request = request;
    }

    public static HttpRequestBuilderWrapper createBuilder() {
        return new HttpRequestBuilderWrapper();
    }

    @HiddenMethod
    public HttpRequest getRequest() {
        return request;
    }
}