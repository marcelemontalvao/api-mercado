package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.Enum.StatusDoPagamento;
import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.dto.request.RequestPedidoDTO;
import br.com.compass.avaliacao.dto.response.ResponsePedidoDTO;
import br.com.compass.avaliacao.exceptions.PedidoNotFoundException;
import br.com.compass.avaliacao.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    public List<PedidoEntity> findAll() {
        List<PedidoEntity> pedido = pedidoRepository.findAll();
        return pedido;
    }

    public ResponsePedidoDTO getById(Long id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        return modelMapper.map(pedidoEntity, ResponsePedidoDTO.class);
    }

    public List<ResponsePedidoDTO> get(String cpf, String ordenar) {
        List<PedidoEntity> pedido = pedidoRepository.findAllPedidos(cpf, Sort.by(replaceOrderStringThroughDirection(ordenar), "id"));
        List<ResponsePedidoDTO> responsePedidoDTOS = pedido.stream().map(pedidos -> modelMapper.map
                (pedidos, ResponsePedidoDTO.class)).collect(Collectors.toList());
        return responsePedidoDTOS;
    }

    public ResponsePedidoDTO save(@Valid RequestPedidoDTO request) {
        PedidoEntity pedidoEntity = modelMapper.map(request, PedidoEntity.class);
        PedidoEntity savedPedidoEntity = pedidoRepository.save(pedidoEntity);
        return modelMapper.map(savedPedidoEntity, ResponsePedidoDTO.class);
    }

    public ResponsePedidoDTO delete(Long id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
            if (pedidoEntity.getStatusDoPagamento().equals(StatusDoPagamento.PROCESSANDO.name())) {
                pedidoRepository.delete(pedidoEntity);
            }else {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }
        return modelMapper.map(pedidoEntity, ResponsePedidoDTO.class);
    }

    public void update(@Valid RequestPedidoDTO request, Long id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        modelMapper.map(request, pedidoEntity);
        pedidoRepository.save(pedidoEntity);
    }

    private Sort.Direction replaceOrderStringThroughDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}