package com.example.grupomercadona;

import java.util.Date;

public class Productos {
    private int precio, stock;
    private String Marca;
    private Date fechaCaducidad;

    public Productos() {
        precio = 0;
        stock = 0;
        Marca = "";
        fechaCaducidad = null;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
