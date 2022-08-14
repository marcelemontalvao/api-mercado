package br.com.compass.api4.services;

import br.com.compass.api4.CodigoSku;
import br.com.compass.api4.dtos.request.RequestItemDTO;
import br.com.compass.api4.dtos.response.ResponseItemDTO;
import br.com.compass.api4.entities.ItemEntity;
import br.com.compass.api4.exceptions.ItemNotFoundException;
import br.com.compass.api4.repositorys.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CodigoSku codigoSku;


    public List<ResponseItemDTO> getAll() {
        List<ItemEntity> allItems = itemRepository.findAll();
        List<ResponseItemDTO> dtos = allItems.stream().map(itemEntity ->
                modelMapper.map(itemEntity, ResponseItemDTO.class)).collect(Collectors.toList());
        return dtos;
    }


    public ResponseItemDTO save(RequestItemDTO requestItemDTO) {
        ItemEntity itemEntity = modelMapper.map(requestItemDTO, ItemEntity.class);
        itemEntity.setSkuid(codigoSku.skuId());
        ItemEntity savedEntity = itemRepository.save(itemEntity);
        return modelMapper.map(savedEntity, ResponseItemDTO.class);
    }



    public ResponseItemDTO update(RequestItemDTO RequestItemDTO, Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        ResponseItemDTO map = modelMapper.map(RequestItemDTO, ResponseItemDTO.class);
        itemRepository.save(itemEntity);
        return map;
    }


}
