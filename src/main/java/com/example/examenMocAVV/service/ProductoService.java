package com.example.examenMocAVV.service;

import com.example.examenMocAVV.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> findAllProductos();
    Optional<Producto> findProducto(Long id);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPrecio(float precio);
    List<Producto> findByPrecioAndCategoria(float precio, String categoria);
    Producto addProducto(Producto producto);
    void eliminarProductoById(Long productoId);
    Producto modificarProducto(Long productoId, Producto producto);

    void deleteProducto(long l);
}
