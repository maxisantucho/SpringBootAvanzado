package com.stc.ec.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    @Column(name="fecha_registro")
    private Date fechaRegistro;

}
