package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.entities.ItemEntity;
import br.com.compass.avaliacao.entities.OfertaEntity;
import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.entities.dto.request.RequestItemDTO;
import br.com.compass.avaliacao.entities.dto.request.RequestOfertaDTO;
import br.com.compass.avaliacao.entities.dto.response.ResponseItemDTO;
import br.com.compass.avaliacao.entities.dto.response.ResponsePedidoDTO;
import br.com.compass.avaliacao.exceptions.OfertaNotFoundException;
import br.com.compass.avaliacao.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("ItemServiceTest")
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("deve atualizar um item sem ofertas")
    public void deveAtualizarUmItemSemOfertas() {
        ItemEntity itemEntity = new ItemEntity();
        RequestItemDTO requestItemDTO = new RequestItemDTO();
        requestItemDTO.setOfertas(null);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));
        itemService.update(requestItemDTO, 1L);
        Mockito.verify(itemRepository).save(itemEntity);
        Mockito.verify(modelMapper).map(itemEntity, ResponseItemDTO.class);
    }

    @Test
    @DisplayName("deve atualizar um item com ofertas")
    public void deveAtualizarUmItemComOfertas() {
        ItemEntity itemEntity = new ItemEntity();
        RequestItemDTO requestItemDTO = new RequestItemDTO();
        List<OfertaEntity> ofertaEntityList = new ArrayList<>();
        OfertaEntity ofertaEntity = new OfertaEntity();
        ofertaEntityList.add(ofertaEntity);
        List<RequestOfertaDTO> requestOfertaDTOS = new ArrayList<>();
        RequestOfertaDTO requestOfertaDTO = new RequestOfertaDTO();
        LocalDateTime dataDeValidade = LocalDateTime.now().plusHours(2);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2012,7,30, 18,0,0);
        requestOfertaDTO.setDataDeCriacao(dataDeCriacao);
        requestOfertaDTO.setDataDeValidade(dataDeValidade);
        requestOfertaDTOS.add(requestOfertaDTO);
        requestItemDTO.setOfertas(requestOfertaDTOS);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));
        itemService.update(requestItemDTO, 1L);
        Mockito.verify(itemRepository).save(itemEntity);
        Mockito.verify(modelMapper).map(itemEntity, ResponseItemDTO.class);
    }

    @Test
    @DisplayName("deve atualizar um item com ofertas com data de validade invalida")
    public void deveAtualizarUmItemComOfertasComDataDeValidadeInvalida() {
        ItemEntity itemEntity = new ItemEntity();
        RequestItemDTO requestItemDTO = new RequestItemDTO();
        List<OfertaEntity> ofertaEntityList = new ArrayList<>();
        OfertaEntity ofertaEntity = new OfertaEntity();
        ofertaEntityList.add(ofertaEntity);
        List<RequestOfertaDTO> requestOfertaDTOS = new ArrayList<>();
        RequestOfertaDTO requestOfertaDTO = new RequestOfertaDTO();
        LocalDateTime dataDeValidade = LocalDateTime.now().minusHours(3);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2032,7,30, 18,0,0);
        requestOfertaDTO.setDataDeCriacao(dataDeCriacao);
        requestOfertaDTO.setDataDeValidade(dataDeValidade);
        requestOfertaDTOS.add(requestOfertaDTO);
        requestItemDTO.setOfertas(requestOfertaDTOS);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));
        Assertions.assertThrows(OfertaNotFoundException.class, () -> itemService.update(requestItemDTO, 1L));
    }
}
