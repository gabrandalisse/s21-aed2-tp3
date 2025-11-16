package org.example.compresion;

public class Nodo {
    private Character caracter;
    private Integer frecuencia;
    private Nodo izq;
    private Nodo der;

    public Nodo(Character caracter, Integer frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izq = null;
        this.der = null;
    }

    public Nodo(Character caracter, Integer frecuencia, Nodo izq, Nodo der) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izq = izq;
        this.der = der;
    }


    public Character getCaracter() {
        return caracter;
    }

    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
}
