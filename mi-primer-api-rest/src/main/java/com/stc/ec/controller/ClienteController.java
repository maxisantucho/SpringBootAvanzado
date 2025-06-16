package com.stc.ec.controller;


import com.stc.ec.model.dao.dto.ClienteDTO;
import com.stc.ec.model.entity.Cliente;
import com.stc.ec.model.payload.MensajeResponse;
import com.stc.ec.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDTO);
            clienteDTO = ClienteDTO.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .objeto(clienteDTO)
                    .build(), HttpStatus.CREATED);
        } catch(DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null).build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        Cliente clienteUpDate = null;
        try {
            if(clienteService.existsById(id)) {
                clienteDTO.setIdCliente(id);
                clienteUpDate = clienteService.save(clienteDTO);
                clienteDTO = ClienteDTO.builder()
                        .idCliente(clienteUpDate.getIdCliente())
                        .nombre(clienteUpDate.getNombre())
                        .apellido(clienteUpDate.getApellido())
                        .correo(clienteUpDate.getCorreo())
                        .fechaRegistro(clienteUpDate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .objeto(clienteDTO)
                        .build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intentas actualizar no se encuentra en la base de datos")
                        .objeto(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch(DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null).build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch(DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null).build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        if(cliente == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe")
                    .objeto(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa")
                .objeto(ClienteDTO.builder()
                        .idCliente(cliente.getIdCliente())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .correo(cliente.getCorreo())
                        .fechaRegistro(cliente.getFechaRegistro())
                        .build()).build(),
                HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> showAll() {
        List<Cliente> getList = clienteService.listAll();
        if(getList.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros")
                    .objeto(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa")
                .objeto(getList).build(),
                HttpStatus.OK);
    }

}
