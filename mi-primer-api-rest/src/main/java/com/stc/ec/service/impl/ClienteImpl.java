package com.stc.ec.service.impl;

import com.stc.ec.model.dao.ClienteDAO;
import com.stc.ec.model.dao.dto.ClienteDTO;
import com.stc.ec.model.entity.Cliente;
import com.stc.ec.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImpl implements IClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDto) {
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
        return clienteDAO.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDAO.existsById(id);
    }

    @Override
    public List<Cliente> listAll() {
        return (List) clienteDAO.findAll();
    }

}
