package br.com.compass.api4.services;

import br.com.compass.api4.dtos.request.RequestCartoesDTO;
import br.com.compass.api4.dtos.response.ResponseCartoesDTO;
import br.com.compass.api4.entities.CartoesEntity;
import br.com.compass.api4.entities.ClienteEntity;
import br.com.compass.api4.exceptions.CartoesNotFoundException;
import br.com.compass.api4.exceptions.ClienteNotFoundException;
import br.com.compass.api4.repositorys.CartoesRepository;
import br.com.compass.api4.repositorys.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartoesService {
    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseCartoesDTO save(RequestCartoesDTO requestCartoesDTO, String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findById(cpf).orElseThrow(ClienteNotFoundException::new);
        CartoesEntity cartoesEntity = modelMapper.map(requestCartoesDTO, CartoesEntity.class);
        List<CartoesEntity> cartoesEntityList = clienteEntity.getCartoesEntityList();
        cartoesEntityList.add(cartoesEntity);
        clienteEntity.setCartoesEntityList(cartoesEntityList);
        CartoesEntity savedEntity = cartoesRepository.save(cartoesEntity);
        return modelMapper.map(savedEntity, ResponseCartoesDTO.class);
    }

    public List<ResponseCartoesDTO> getAll(String cpf) {
        List<CartoesEntity> allCartoes = cartoesRepository.findAll();
        List<ResponseCartoesDTO> dtos = allCartoes.stream().map(clienteEntity ->
                modelMapper.map(clienteEntity, ResponseCartoesDTO.class)).collect(Collectors.toList());
        return dtos;
    }

    public ResponseCartoesDTO update(RequestCartoesDTO requestCartoesDTO, Long id) {
        CartoesEntity cartoesEntity = cartoesRepository.findById(id).orElseThrow(CartoesNotFoundException::new);
        ResponseCartoesDTO map = modelMapper.map(requestCartoesDTO, ResponseCartoesDTO.class);
        cartoesRepository.save(cartoesEntity);
        return map;
    }
}
