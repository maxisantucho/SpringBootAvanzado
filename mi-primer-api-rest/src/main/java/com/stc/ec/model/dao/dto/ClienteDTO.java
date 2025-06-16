package com.stc.ec.model.dao.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
public class ClienteDTO {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaRegistro;

}
