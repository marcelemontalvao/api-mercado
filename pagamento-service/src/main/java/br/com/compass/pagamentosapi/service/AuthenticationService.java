package br.com.compass.pagamentosapi.service;

import br.com.compass.pagamentosapi.dto.RequestAuthenticator;
import br.com.compass.pagamentosapi.dto.ResponseAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthenticationService {
    private static String urlAutenticator = "https://pb-getway-payment.herokuapp.com/v1/auth";

    @Autowired
    private WebClient.Builder webclient;

    public ResponseAuthenticator authenticator() {
        RequestAuthenticator request = new RequestAuthenticator();
        return webclient.build().
                post().
                uri(urlAutenticator).
                bodyValue(request).
                retrieve().bodyToMono(ResponseAuthenticator.class).block();
    }
}

