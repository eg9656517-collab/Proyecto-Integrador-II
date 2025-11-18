/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author lapto
 */
public class DatosCompartidos {

    // Lista de productos compartida entre formularios
    public static ArrayList<Producto> listaProducto = new ArrayList<>();

    // 🔹 Agrega un producto nuevo
    public static void agregarProducto(Producto p) {
        listaProducto.add(p);
    }

    // 🔹 Devuelve todos los productos
    public static ArrayList<Producto> obtenerProductos() {
        return listaProducto;
    }

    // 🔹 Buscar producto por nombre (coincidencia exacta o parcial)
    public static Producto buscarProductoPorNombre(String nombreBuscado) {
        for (Producto p : listaProducto) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p; // Coincidencia exacta
            }
        }

        // Coincidencia parcial (opcional)
        for (Producto p : listaProducto) {
            if (p.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase())) {
                return p;
            }
        }

        return null; // Si no se encuentra
    }
}



