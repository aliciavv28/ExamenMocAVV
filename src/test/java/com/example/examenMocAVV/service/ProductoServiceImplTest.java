package com.example.examenMocAVV.service;

import com.example.examenMocAVV.entity.Producto;
import com.example.examenMocAVV.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoServiceImpl productoService;

    @Test
    void findAllProductos() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();

        Mockito.when(productoRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Producto> resultado = productoService.findAllProductos();

        Assertions.assertEquals(2, resultado.size());
        Mockito.verify(productoRepository).findAll();
    }

    @Test
    void findProducto() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.findProducto(1L);

        Assertions.assertTrue(resultado.isPresent());
        Mockito.verify(productoRepository).findById(1L);
    }

    @Test
    void findByCategoria() {
        Mockito.when(productoRepository.findByCategoria("Electrónica")).thenReturn(List.of(new Producto()));

        List<Producto> resultado = productoService.findByCategoria("Electrónica");

        Assertions.assertEquals(1, resultado.size());
        Mockito.verify(productoRepository).findByCategoria("Electrónica");
    }

    @Test
    void findByPrecio() {
        Mockito.when(productoRepository.findByPrecio(12f)).thenReturn(List.of(new Producto()));

        List<Producto> resultado = productoService.findByPrecio(12f);

        Assertions.assertEquals(1, resultado.size());
        Mockito.verify(productoRepository).findByPrecio(12f);
    }

    @Test
    void findByPrecioAndCategoria() {
        Mockito.when(productoRepository.findByPrecioAndCategoria(12f, "Electronica")).thenReturn(List.of(new Producto()));

        List<Producto> resultado =
                productoService.findByPrecioAndCategoria(12f, "Electronica");

        Assertions.assertEquals(1, resultado.size());
        Mockito.verify(productoRepository).findByPrecioAndCategoria(12f, "Electronica");
    }

    @Test
    void addProducto() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.addProducto(producto);

        Assertions.assertNotNull(resultado);
        Mockito.verify(productoRepository).save(producto);
    }

    @Test
    void eliminarProductoById() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.eliminarProductoById(1L);

        Mockito.verify(productoRepository).delete(producto);
    }

    @Test
    void modificarProducto() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Mockito.when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.modificarProducto(1L, producto);

        Assertions.assertNotNull(resultado);
        Mockito.verify(productoRepository).save(producto);
    }
}