package com.stc.ec.service;

import com.stc.ec.model.dao.dto.ClienteDTO;
import com.stc.ec.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAll();

    Cliente save(ClienteDTO cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);

}
