package br.com.compass.pagamentosapi.service;

import br.com.compass.pagamentosapi.dto.*;
import br.com.compass.pagamentosapi.entity.PagamentoEntity;
import br.com.compass.pagamentosapi.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PagamentoService {

    private static String urlSolicitacao = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private AuthenticationService authenticator;

    private RequestPagamentoDTO criaRequestPagamento(PagamentosDTO pagamentosDTO) {
        RequestPagamentoDTO requestPagamentoDTO = new RequestPagamentoDTO();
        Customer customer = new Customer();
        Card card = new Card();

        requestPagamentoDTO.setCustomer(customer);

        requestPagamentoDTO.setCard(card);

        requestPagamentoDTO.setTransactionAmount(pagamentosDTO.getPedidoValorTotal());
        requestPagamentoDTO.getCustomer().setDocumentNumber(pagamentosDTO.getCpf());
        requestPagamentoDTO.getCard().setBrand(pagamentosDTO.getMarca());
        requestPagamentoDTO.getCard().setCardholderName(pagamentosDTO.getNomeDoCartao());
        requestPagamentoDTO.getCard().setExpirationMonth(pagamentosDTO.getMesDeExpiracao());
        requestPagamentoDTO.getCard().setSecurityCode(pagamentosDTO.getCodigoDeSeguranca());
        requestPagamentoDTO.getCard().setNumberToken(pagamentosDTO.getNumeroDoCartao());
        requestPagamentoDTO.getCard().setExpirationYear(pagamentosDTO.getAnoDeExpiracao());
        return requestPagamentoDTO;
    }

    public void add (PagamentosDTO pagamentosDTO){
        PagamentoEntity entity = modelMapper.map(pagamentosDTO, PagamentoEntity.class);
        pagamentoRepository.save(entity);
    }

    public ResponsePagamentoDTO sendGateway (PagamentosDTO pagamentosDTO) {
        RequestPagamentoDTO request = criaRequestPagamento(pagamentosDTO);
        String accessToken = authenticator.authenticator().getAccessToken();
        return webClient.build().
                post()
                .uri(urlSolicitacao)
                .bodyValue(request)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(ResponsePagamentoDTO.class)
                .block();
    }

}
