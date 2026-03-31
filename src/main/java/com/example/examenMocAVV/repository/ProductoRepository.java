package com.example.examenMocAVV.repository;

import com.example.examenMocAVV.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecio(float precio);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPrecioAndCategoria(float precio, String categoria);
}
