package com.example.restaurantegrupal;

public class Caracteristicas {
    private boolean alergenos = false;
    private String tipoAlergenos = null, precio = null;

    public Caracteristicas() {
        super();
    }

    public Caracteristicas( String precio, boolean alergenos, String tipoAlergenos) {
        this.alergenos = alergenos;
        this.tipoAlergenos = tipoAlergenos;
        this.precio = precio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isAlergenos() {
        return alergenos;
    }

    public void setAlergenos(boolean alergenos) {
        this.alergenos = alergenos;
    }

    public String getTipoAlergenos() {
        return tipoAlergenos;
    }

    public void setTipoAlergenos(String tipoAlergenos) {
        this.tipoAlergenos = tipoAlergenos;
    }
}
