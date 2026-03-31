package com.example.examenMocAVV.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String categoria;

    @Column
    private float precio;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column
    private String observaciones;

    @Column
    private Integer cantidad;
}
