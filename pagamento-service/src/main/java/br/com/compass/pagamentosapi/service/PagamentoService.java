package br.com.compass.pagamentosapi.service;

import br.com.compass.pagamentosapi.dto.PagamentosDTO;
import br.com.compass.pagamentosapi.entity.PagamentoEntity;
import br.com.compass.pagamentosapi.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void add (PagamentosDTO pagamentosDTO){
        PagamentoEntity entity = modelMapper.map(pagamentosDTO, PagamentoEntity.class);
        pagamentoRepository.save(entity);
        RequestPagamento();
    }

    public void RequestPagamento() {
        String Auth = AuthenticationService.getUrlAuthenticator();
        System.out.println("Aqui é o auth " + Auth);
    }
}
