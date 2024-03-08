package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="clientes_tb")
@Data
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula" , unique = true ,length = 8, nullable = false)
    private String Cedula;

    @Column(name="nombre", nullable=false)
    private String Nombre;

    @Column(name="apellido", nullable=false)
    private String Apellido;

    @Column(unique=true, nullable=false)
    private String email;

    private String state;
}

