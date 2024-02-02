package com.example.grupomercadona;

import java.util.ArrayList;
import java.util.Arrays;

public class Categorias {
    private ArrayList<String> Lacteos, Carnes, CuidadoPersonal, ProductosLimpieza,
            Panaderia, Fruteria, Categories;

    public Categorias() {
        //Constructor
        super();
        Lacteos = new ArrayList<>(Arrays.asList("Leche", "Yogures", "Quesos", "Mantequilla",
                "Huevos"));
        Carnes = new ArrayList<>(Arrays.asList("Carne fresca de pollo", "Carne fresca de ternera",
                "Carne fresca de cerdo", "Carne fresca de cordero", "Embutidos", "Carnes procesadas"));
        CuidadoPersonal = new ArrayList<>(Arrays.asList("Champú", "Acondicionador", "Jabón",
                "Gel de baño", "Crema de manos", "Locion", "Producto de afeitado",
                "Pasta de dientes", "Cepillos"));
        ProductosLimpieza = new ArrayList<>(Arrays.asList("Detergente", "Limpiador multiusos",
                "Papel higiénico", "Toallas de papel"));
        Panaderia = new ArrayList<>(Arrays.asList("Pan de molde", "Pan fresco", "Cereales",
                "Harina", "Arroz", "Pasta"));
        Fruteria = new ArrayList<>(Arrays.asList("Manzanas", "Plátanos", "Uvas", "Fresas",
                "Tomates", "Lechugas", "Zanahorias", "Brócoli"));
        Categories = new ArrayList<>(Arrays.asList("Lacteos", "Carnes", "CuidadoPersonal",
                "ProductosLimpieza", "Panaderia", "Fruteria"));
    }

    // Métodos para acceder a las listas o modificarlas según sea necesario
    public ArrayList<String> getLacteos() {
        return Lacteos;
    }

    public ArrayList<String> getCarnes() {
        return Carnes;
    }

    public ArrayList<String> getCuidadoPersonal() {
        return CuidadoPersonal;
    }

    public ArrayList<String> getProductosLimpieza() {
        return ProductosLimpieza;
    }

    public ArrayList<String> getPanaderia() {
        return Panaderia;
    }

    public ArrayList<String> getFruteria() {
        return Fruteria;
    }
    public ArrayList<String> getCategories() {
        return Categories;
    }
}
