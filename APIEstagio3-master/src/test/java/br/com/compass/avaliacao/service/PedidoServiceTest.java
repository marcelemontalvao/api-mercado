package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.dto.request.RequestPedidoDTO;
import br.com.compass.avaliacao.dto.response.ResponsePedidoDTO;
import br.com.compass.avaliacao.exceptions.PedidoNotFoundException;
import br.com.compass.avaliacao.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("PedidoServiceTest")
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    @DisplayName("deve remover um pedido")
    public void deveRemoverPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.delete(1L);
        Mockito.verify(pedidoRepository).delete(pedidoEntity);
        Mockito.verify(modelMapper).map(pedidoEntity, ResponsePedidoDTO.class);
    }

    @Test
    @DisplayName("erro ao remover um pedido ")
    public void erroAoRemoverUmPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.delete(1L);
        Assertions.assertThrows(PedidoNotFoundException.class,  ()-> pedidoService.delete(20L));
    }

    @Test
    @DisplayName("deve achar um pedido")
    public void deveAcharPedidoPeloId() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.getById(1L);
        Mockito.verify(modelMapper).map(pedidoEntity, ResponsePedidoDTO.class);
    }

    @Test
    @DisplayName("erro ao achar um pedido")
    public void erroAoAcharUmPedidoPeloId() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.getById(1L);
        Assertions.assertThrows(PedidoNotFoundException.class,  ()-> pedidoService.getById(20L));
    }

    @Test
    @DisplayName("deve atualizar um pedido")
    public void deveAtualizarUmPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.update(requestPedidoDTO, 1L);
        Mockito.verify(pedidoRepository).save(pedidoEntity);
    }

    @Test
    @DisplayName("erro ao atualizar um pedido")
    public void erroAoAtualizarUmPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        pedidoService.update(requestPedidoDTO, 1L);
        Assertions.assertThrows(PedidoNotFoundException.class,  ()-> pedidoService.update(requestPedidoDTO, 20L));
    }

    @Test
    @DisplayName("deve salvar um pedido")
    public void deveSalvarUmPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        Mockito.when(modelMapper.map(requestPedidoDTO, PedidoEntity.class)).thenReturn(pedidoEntity);
        Mockito.when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);
        pedidoService.save(requestPedidoDTO);
        Mockito.verify(pedidoRepository).save(pedidoEntity);
        Mockito.verify(modelMapper).map(pedidoEntity, ResponsePedidoDTO.class);
    }

    @Test
    @DisplayName("deve achar os pedidos")
    public void deveAcharTodosOsPedidos() {
        pedidoService.findAll();
        Mockito.verify(pedidoRepository).findAll();
    }


}
