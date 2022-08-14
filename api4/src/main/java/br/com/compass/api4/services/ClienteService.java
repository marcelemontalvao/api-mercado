package br.com.compass.api4.services;

import br.com.compass.api4.dtos.request.RequestClienteDTO;
import br.com.compass.api4.dtos.response.ResponseClienteDTO;
import br.com.compass.api4.entities.ClienteEntity;
import br.com.compass.api4.exceptions.ClienteNotFoundException;
import br.com.compass.api4.repositorys.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseClienteDTO save(RequestClienteDTO requestClienteDTO) {
        ClienteEntity clienteEntity = modelMapper.map(requestClienteDTO, ClienteEntity.class);
        ClienteEntity savedEntity = clienteRepository.save(clienteEntity);
        return modelMapper.map(savedEntity, ResponseClienteDTO.class);
    }

    public ResponseClienteDTO getCliente(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findById(cpf).orElseThrow(ClienteNotFoundException::new);
        return modelMapper.map(clienteEntity, ResponseClienteDTO.class);
    }

    public ResponseClienteDTO update(@Valid RequestClienteDTO requestClienteDTO, String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findById(cpf).orElseThrow(ClienteNotFoundException::new);
        ResponseClienteDTO map = modelMapper.map(requestClienteDTO, ResponseClienteDTO.class);
        clienteRepository.save(clienteEntity);
        return map;
    }
}
