package com.techlab.ricoysano;

import com.techlab.ricoysano.exception.ProductoNoEncontradoException;
import com.techlab.ricoysano.exception.StockInsuficienteException;
import com.techlab.ricoysano.model.Producto;
import com.techlab.ricoysano.service.ProductoService;
import com.techlab.ricoysano.ui.MenuProducto;
import com.techlab.ricoysano.util.Validador;

import java.util.Scanner;

 class Main {

    public static void main(String[] args) {
      
        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);

        cargarDatosDePrueba(service);

        int opcion;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

          
            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.buscarProducto();
                    case 4 -> menu.actualizarProducto();
                    case 5 -> menu.eliminarProducto();
                    case 6 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 6.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
     
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                
                System.out.println("Dato inválido: " + e.getMessage());
            }

            System.out.println(); 

        } while (opcion != 6);

        sc.close();
    }

    private static void cargarDatosDePrueba(ProductoService service) {
        service.guardar(new Producto("Pan Lactal 500g", 10000, 10, "Salado"));
        service.guardar(new Producto("Pan de Hamburguesa x4", 10000, 4, "Salado"));
        service.guardar(new Producto("Pre-Pizza Gde", 6000, 8, "Salado"));
        service.guardar(new Producto("Tarta Frutal", 20000, 4, "Dulce"));
        service.guardar(new Producto("Medialunas", 15000, 5, "Dulce"));
        System.out.println("✔ Se cargaron 5 productos de prueba.\n");
    }
}