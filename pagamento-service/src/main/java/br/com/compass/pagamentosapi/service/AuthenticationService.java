package br.com.compass.pagamentosapi.service;

import br.com.compass.pagamentosapi.dto.RequestAuthenticator;
import br.com.compass.pagamentosapi.dto.ResponseAuthenticator;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthenticationService {
    private static String urlAutenticator = "https://pb-getway-payment.herokuapp.com/v1/auth";

    public static String getUrlAuthenticator() {
        WebClient webClient = WebClient.create();
        RequestAuthenticator requestAuthenticator = new RequestAuthenticator();
        ResponseAuthenticator responseAuthenticator = webClient
                .post()
                .uri(urlAutenticator)
                .bodyValue(requestAuthenticator)
                .retrieve()
                .bodyToMono(ResponseAuthenticator.class)
                .block();
        return responseAuthenticator.getAccessToken();
    }
}

