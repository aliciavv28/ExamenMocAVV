package com.example.examenMocAVV.controller;

import com.example.examenMocAVV.entity.Producto;
import com.example.examenMocAVV.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// IMPORTACIONES CRÍTICAS CORREGIDAS:
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Test
    void getProductos_sinFiltros_devuelveLista() throws Exception {
        Producto producto = new Producto(
                1L,
                "Teclado",
                "Mecánico",
                "Tecnologia",
                50.0f,
                LocalDate.now(),
                "Ninguna",
                5
        );

        when(productoService.findAllProductos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Teclado"));
    }

    @Test
    void getProducto_porId_devuelveProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Ratón");

        when(productoService.findProducto(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ratón"));
    }

    @Test
    void deleteProducto_funciona() throws Exception {
        // En Mockito, por defecto los métodos void no hacen nada,
        // pero es buena práctica especificarlo.
        doNothing().when(productoService).deleteProducto(1L);

        mockMvc.perform(delete("/api/producto/1"))
                .andExpect(status().isOk());
    }
}