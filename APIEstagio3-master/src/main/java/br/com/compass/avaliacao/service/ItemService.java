package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.entities.ItemEntity;
import br.com.compass.avaliacao.entities.OfertaEntity;
import br.com.compass.avaliacao.dto.request.RequestItemDTO;
import br.com.compass.avaliacao.dto.request.RequestOfertaDTO;
import br.com.compass.avaliacao.dto.response.ResponseItemDTO;
import br.com.compass.avaliacao.exceptions.ItemNotFoundException;
import br.com.compass.avaliacao.exceptions.OfertaNotFoundException;
import br.com.compass.avaliacao.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseItemDTO update(RequestItemDTO request, Long id) throws OfertaNotFoundException {

        ItemEntity entity = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(request, entity);
        entity.setId(id);
        if(request.getOfertas() != null && !request.getOfertas().isEmpty()) {
            for (RequestOfertaDTO oferta : request.getOfertas()
        ) {
            if (oferta.getDataDeValidade().isBefore(LocalDateTime.now())) {
                throw new OfertaNotFoundException();
            }
        }
            entity.setOfertas(new ArrayList<>());
            for (RequestOfertaDTO oferta: request.getOfertas()
            ) {
                var ofertaEntity = modelMapper.map(oferta, OfertaEntity.class);
                entity.getOfertas().add(ofertaEntity);
            }
        }
        itemRepository.save(entity);
        return modelMapper.map(entity, ResponseItemDTO.class);
    }
}
